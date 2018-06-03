package com.huishu.analysis.impl;

import com.huishu.analysis.vo.NewsVO;
import com.huishu.analysis.vo.ValidationVO;
import com.huishu.config.AnalysisConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.KingBaseDgap;
import com.huishu.entity.SiteLib;
import com.huishu.entity.Wechat;
import com.huishu.service.SiteLibService;
import com.huishu.service.WechatService;
import com.huishu.utils.DateUtils;
import com.huishu.vo.DgapData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 分析微信数据
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("wechatAnalyzer")
public class WechatAnalyzer extends DefaultAnalyzer {

    private static final List<DgapData> STATIC_LIST = new ArrayList<DgapData>();

    @Autowired
    private WechatService wechatService;
    @Autowired
    private SiteLibService siteLibService;

    @Override
    public String getName() {
        return SysConst.WECHAT;
    }

    @Override
    public void analysis(AnalysisConfig analysisConfig, ThreadPoolExecutor executor, Map<String, String> indexMap) {
        STATIC_LIST.clear();
        if (analysisConfig.isWechatMark()) {
            for (int i = 0; i < analysisConfig.getWechatThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{}微信分析开始", currentThread.getName(), currentThread.getId());
                    try {
                        analysisData(analysisConfig, indexMap, pageNumber);
                    } catch (Exception e) {
                        logger.error("微信数据分析异常", e);
                    }
                    logger.info("{}:{}微信分析结束", currentThread.getName(), currentThread.getId());
                });
            }
        }
    }

    /**
     * 分析数据
     *
     * @param analysisConfig
     * @param indexMap
     * @param pageNumber
     */
    private void analysisData(AnalysisConfig analysisConfig, Map<String, String> indexMap, int pageNumber) {
        Wechat entity = new Wechat();
        entity.setId(Long.valueOf(indexMap.get(SysConst.WECHAT)));
        Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
        List<Wechat> list = wechatService.findOneHundred(entity, pageable);

        logger.info("微信分析,读取 {} 条", list.size());

        if (list.size() <= 0) {
            return;
        }

        String newId = list.get(list.size() - 1).getId() + "";
        String oldId = indexMap.get(SysConst.WECHAT);
        Map<String, String> newIndexMap = new HashMap<>(indexMap);
        if (Long.parseLong(newId) > Long.parseLong(oldId)) {
            newIndexMap.put(SysConst.WECHAT, newId);
        }


        List<DgapData> saveList = new ArrayList<DgapData>();
        List<Wechat> readList = new ArrayList<Wechat>();
        List<KingBaseDgap> historyList = new ArrayList<KingBaseDgap>();
        for (Wechat item : list) {
            if (isNotExists(STATIC_LIST, item.getUrls())) {
                // 分析
                SiteLib site = siteLibService.findByName(item.getAuthor());
                SiteLib newSite = fillAreaInfoForSiteLib(item.getTitle(), item.getContent(), site);
                if (newSite != null) {
                    ValidationVO validationVO = ValidationVO.create(item, newSite);
                    if (validate(validationVO)) {
                        NewsVO newsVO = NewsVO.create(item, newSite);
                        DgapData dgapData = fillDgapData(newsVO);
                        item.setIsRead(SysConst.ESDataStatus.EXISTS_IN_ES.getCode());
                        addKingBaseData(historyList, dgapData);
                        dgapData.setId(String.valueOf(item.getId()));
                        saveList.add(dgapData);
                        STATIC_LIST.add(dgapData);
                    }
                    if (SysConst.ESDataStatus.NOT_EXISTS_IN_ES.getCode().equals(item.getIsRead())) {
                        item.setIsRead(SysConst.ESDataStatus.EXCEPTION.getCode());
                    }
                    readList.add(item);
                }
            }
        }

        if (saveList.size() > 0) {
            saveToFile(saveList, SysConst.WECHAT, analysisConfig.getSourceMorePath());
            saveToKingBase(historyList);
        }
        if (readList.size() > 0) {
            wechatService.save(readList);
        }

        logger.info("微信分析,入库 {} 条", saveList.size());
        logger.info("微信分析,分析 {} 条", readList.size());

        recordNum(newIndexMap);
    }

    @Override
    protected boolean validate(ValidationVO validationVO) {
        String publishDate = validationVO.getFldrecddate();
        if (StringUtils.isEmpty(validationVO.getProvince())
                || StringUtils.isEmpty(validationVO.getIndustry())
                || StringUtils.isEmpty(validationVO.getFldtitle())
                || StringUtils.isEmpty(validationVO.getFldcontent())
                || StringUtils.isEmpty(publishDate)) {
            return false;
        }

        String tempTime = com.huishu.utils.StringUtils.transformTime(publishDate);
        int yearIndex = tempTime.indexOf("-");
        if (yearIndex <= 0) {
            return false;
        }
        int monthIndex = tempTime.indexOf("-", yearIndex + 1);
        if (monthIndex <= 0) {
            return false;
        }

        return true;
    }

    @Override
    protected DgapData fillDgapData(NewsVO newsVO) {
        DgapData result = super.fillDgapData(newsVO);

        result.setPublishType(SysConst.PublishType.WECHAT.getCode());
        result.setDataType(SysConst.DataType.POLICY.getCode());
        String publishDate = DateUtils.getFormatTime(newsVO.getPostTime());
        result.setTime(publishDate);
        fillDateInfoOfDgapData(result, publishDate);
        result.setSite(newsVO.getAuthor());
        result.setSocialChannel(SysConst.SocialChannel.SOCIAL.getCode());
        result.setReportType(SysConst.SiteType.SOCIAL.getCode());
        int count = Integer.parseInt(newsVO.getViewNum()) + Integer.parseInt(newsVO.getLikeNum());
        if (count > SysConst.HOT_EVENT_THRESHOLD) {
            result.setHotEventMark(SysConst.HotEventMark.HOT_EVENT.getCode());
        } else {
            result.setHotEventMark(SysConst.HotEventMark.NOT_HOT_EVENT.getCode());
        }
        result.setHitNum(Long.valueOf(newsVO.getViewNum()));
        String emotion = searchEmotion(newsVO.getTitle(), newsVO.getContent());
        if (SysConst.Emotion.NEUTRAL.getEmotion().equals(emotion)) {
            result.setEmotionMark(SysConst.Emotion.NEUTRAL.getCode());
        }
        if (SysConst.Emotion.NEGATIVE.getEmotion().equals(emotion)) {
            result.setEmotionMark(SysConst.Emotion.NEGATIVE.getCode());
        }
        if (SysConst.Emotion.POSITIVE.getEmotion().equals(emotion)) {
            result.setEmotionMark(SysConst.Emotion.POSITIVE.getCode());
        }
        // 标题
        result.setPolicyTitle(newsVO.getTitle());
        // url
        result.setPolicyUrl(newsVO.getUrls());
        result.setContent(com.huishu.utils.StringUtils.removeHtmlTag(newsVO.getContent()));
        // 阅读量
        result.setReadNum(Long.valueOf(newsVO.getViewNum()));
        // 点击量
        result.setHitNum(Long.valueOf(newsVO.getLikeNum()));

        return result;
    }

}
