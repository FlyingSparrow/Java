package com.huishu.analysis.impl;

import com.huishu.config.AnalysisConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.KingBaseDgap;
import com.huishu.entity.PolicyBak;
import com.huishu.entity.SiteLib;
import com.huishu.service.PolicyBakService;
import com.huishu.service.SiteLibService;
import com.huishu.vo.DgapData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 分析政策数据
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("policyAnalyzer")
public class PolicyAnalyzer extends DefaultAnalyzer {

    private static List<DgapData> policyStaticList = new ArrayList<DgapData>();

    @Autowired
    private PolicyBakService policyBakService;
    @Autowired
    private SiteLibService siteLibService;

    @Override
    public String getName() {
        return SysConst.POLICY;
    }

    @Override
    public void analysis(AnalysisConfig analysisConfig, ThreadPoolExecutor executor, Map<String, String> indexMap) {
        if (analysisConfig.isPolicyMark()) {
            for (int i = 0; i < analysisConfig.getPolicyThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{}政策数据分析开始", currentThread.getName(), currentThread.getId());
                    try {
                        analysisData(analysisConfig, indexMap, pageNumber);
                    } catch (Exception e) {
                        logger.error("政策数据分析异常", e);
                    }
                    logger.info("{}:{}政策数据分析结束", currentThread.getName(), currentThread.getId());
                });
            }
        }
    }

    /**
     * 分析数据
     * @param analysisConfig
     * @param indexMap
     * @param pageNumber
     */
    private void analysisData(AnalysisConfig analysisConfig, Map<String, String> indexMap, int pageNumber) {
        // 1、获取数据 是否重复数据
        PolicyBak news = new PolicyBak();
        news.setId(Long.valueOf(indexMap.get(SysConst.POLICY)));
        Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
        List<PolicyBak> newsList = policyBakService.findOneHundred(news, pageable);

        logger.info("政策分析,读取 {} 条", newsList.size());

        if (newsList.size() <= 0) {
            return;
        }

        String newId = newsList.get(newsList.size() - 1).getId() + "";
        String oldId = indexMap.get(SysConst.POLICY);
        if (Long.valueOf(newId) > Long.valueOf(oldId)) {
            indexMap.put(SysConst.POLICY, newId);
        }

        List<DgapData> saveList = new ArrayList<DgapData>();
        List<KingBaseDgap> historyList = new ArrayList<KingBaseDgap>();
        List<PolicyBak> readList = new ArrayList<PolicyBak>();
        for (PolicyBak item : newsList) {
            if (isNotExists(item.getFldUrlAddr())) {
                // 分析
                SiteLib site = siteLibService.findByName(item.getWebname());
                SiteLib newSite = fillAreaInfoForSiteLib(item.getFldtitle(), item.getFldcontent(), site);
                if (newSite != null) {
                    DgapData dgapData = fillDgapData(item, newSite);
                    if (dgapData != null) {
                        item.setBiaoShi(SysConst.ESDataStatus.EXISTS_IN_ES.getCode());
                        addKingBaseData(historyList, dgapData);
                        dgapData.setId(String.valueOf(item.getId()));
                        saveList.add(dgapData);
                        policyStaticList.add(dgapData);
                    }
                }
                if (SysConst.ESDataStatus.NOT_EXISTS_IN_ES.getCode().equals(item.getBiaoShi())) {
                    item.setBiaoShi(SysConst.ESDataStatus.EXCEPTION.getCode());
                }
                readList.add(item);
            }
        }


        if (saveList.size() > 0) {
            saveToFile(saveList, SysConst.POLICY, analysisConfig.getSourceMorePath());
            saveToKingbase(historyList);
        }
        if (readList.size() > 0) {
            policyBakService.save(readList);
        }

        logger.info("政策分析,入库 {} 条", saveList.size());
        logger.info("政策分析,分析 {} 条", readList.size());

        recordNum(indexMap);
    }

    private boolean isNotExists(String url) {
        boolean flag = true;
        for (DgapData item : policyStaticList) {
            if (StringUtils.isNotEmpty(item.getPolicyUrl())
                    && item.getPolicyUrl().equals(url)) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    private DgapData fillDgapData(PolicyBak policyBak, SiteLib siteLib) {
        if (!validate(policyBak, siteLib)) {
            return null;
        }

        DgapData result = new DgapData();

        result.setPublishType(SysConst.PublishType.LOCAL.getCode());
        // 分类
        result.setDataType(SysConst.DataType.POLICY.getCode());
        String singleData = policyBak.getFldrecddate();
        result.setTime(policyBak.getFldrecddate());
        // 时间
        fillDateInfoOfDgapData(result, singleData);
        // 站点
        result.setSite(policyBak.getWebname());
        // 省份
        result.setProvince(siteLib.getProvince());
        result.setArea(siteLib.getArea());
        // 行业
        result.setIndustry(siteLib.getIndustry());
        // 社会渠道(1,网络媒体,2,论坛,3,社交，4,外媒)
        result.setSocialChannel(SysConst.SocialChannel.INTERNET_MEDIA.getCode());

        // 是否社交网站
        result.setReportType(SysConst.SiteType.MEDIA.getCode());
        // 是否热点 评论 点击 转发 超过1000
        int count = Integer.valueOf(policyBak.getFldHits())
                + Integer.valueOf(policyBak.getFldReply());
        if (count > SysConst.HOT_EVENT_THRESHOLD) {
            result.setHotEventMark(SysConst.HotEventMark.HOT_EVENT.getCode());
        } else {
            result.setHotEventMark(SysConst.HotEventMark.NOT_HOT_EVENT.getCode());
        }

        // 内容 分析 文章 图片 视频
        // 关注量
        result.setHitNum(Long.valueOf(policyBak.getFldHits()));

        String emotion = searchEmotion(policyBak.getFldtitle(), policyBak.getFldcontent());
        if (SysConst.Emotion.NEUTRAL.getEmotion().equals(emotion)) {
            result.setEmotionMark(SysConst.Emotion.NEUTRAL.getCode());
        }
        if (SysConst.Emotion.NEGATIVE.getEmotion().equals(emotion)) {
            result.setEmotionMark(SysConst.Emotion.NEGATIVE.getCode());
        }
        if (SysConst.Emotion.POSITIVE.getEmotion().equals(emotion)) {
            result.setEmotionMark(SysConst.Emotion.POSITIVE.getCode());
        }

        fillPolicyInfo(policyBak.getFldcontent(), policyBak.getWebname(), policyBak.getPdmc(),
                policyBak.getFldUrlAddr(), result);
        // 标题
        result.setPolicyTitle(policyBak.getFldtitle());

        result.setContent(com.huishu.utils.StringUtils.removeHtmlTag(policyBak.getFldcontent()));
        // url
        result.setPolicyUrl(policyBak.getFldUrlAddr());
        // 阅读量
        result.setReadNum(Long.valueOf(policyBak.getFldHits()));
        // 评论量
        result.setHitNum(Long.valueOf(policyBak.getFldReply()));
        setReportType(result);

        return result;
    }

    private boolean validate(PolicyBak policyBak, SiteLib siteLib){
        if (StringUtils.isEmpty(siteLib.getProvince())
                || StringUtils.isEmpty(siteLib.getIndustry())) {
            return false;
        }

        String fldrecddate = policyBak.getFldrecddate();
        if (StringUtils.isEmpty(policyBak.getFldcontent())
                || StringUtils.isEmpty(policyBak.getFldtitle())
                || StringUtils.isEmpty(policyBak.getFldUrlAddr())
                || StringUtils.isEmpty(fldrecddate)) {
            return false;
        }

        int yearIndex = fldrecddate.indexOf("-");
        if (yearIndex <= 0) {
            return false;
        }
        int monthIndex = fldrecddate.indexOf("-", yearIndex + 1);
        if (monthIndex <= 0) {
            return false;
        }

        return true;
    }

}
