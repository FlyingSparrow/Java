package com.huishu.analysis.impl;

import com.huishu.analysis.vo.NewsVO;
import com.huishu.analysis.vo.ValidationVO;
import com.huishu.config.AnalysisConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.KingBaseDgap;
import com.huishu.entity.SiteLib;
import com.huishu.entity.VideoBak;
import com.huishu.service.SiteLibService;
import com.huishu.service.VideoBakService;
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
 * 分析视频数据
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("videoAnalyzer")
public class VideoAnalyzer extends DefaultAnalyzer {

    private static final List<DgapData> STATIC_LIST = new ArrayList<DgapData>();

    @Autowired
    private VideoBakService videoService;
    @Autowired
    private SiteLibService siteLibService;

    @Override
    public String getName() {
        return SysConst.VIDEO;
    }

    @Override
    public void analysis(AnalysisConfig analysisConfig, ThreadPoolExecutor executor, Map<String, String> indexMap) {
        STATIC_LIST.clear();
        if (analysisConfig.isVideoMark()) {
            for (int i = 0; i < analysisConfig.getVideoThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{}视频数据分析开始", currentThread.getName(), currentThread.getId());
                    try {
                        analysisVideo(analysisConfig, indexMap, pageNumber);
                    } catch (Exception e) {
                        logger.error("视频数据分析异常", e);
                    }
                    logger.info("{}:{}视频数据分析结束", currentThread.getName(), currentThread.getId());
                });
            }
        }
    }

    private void analysisVideo(AnalysisConfig analysisConfig, Map<String, String> indexMap, int pageNumber) {
        VideoBak entity = new VideoBak();
        entity.setId(Long.valueOf(indexMap.get(SysConst.VIDEO)));
        Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
        List<VideoBak> list = videoService.findOneHundred(entity, pageable);

        logger.info("视频分析,读取 {} 条", list.size());

        if (list.size() <= 0) {
            return;
        }

        String newId = list.get(list.size() - 1).getId() + "";
        String oldId = indexMap.get(SysConst.VIDEO);
        Map<String, String> newIndexMap = new HashMap<>(indexMap);
        if (Long.parseLong(newId) > Long.parseLong(oldId)) {
            newIndexMap.put(SysConst.VIDEO, newId);
        }


        List<DgapData> saveList = new ArrayList<DgapData>();
        List<com.huishu.entity.VideoBak> readList = new ArrayList<com.huishu.entity.VideoBak>();
        List<KingBaseDgap> historyList = new ArrayList<KingBaseDgap>();
        for (VideoBak item : list) {
            if (isNotExists(STATIC_LIST, item.getFldUrlAddr())) {
                // 分析
                SiteLib site = siteLibService.findByName(item.getWebname());
                SiteLib newSite = fillAreaInfoForSiteLib(item.getName(), item.getWebname(), site);
                if (newSite != null) {
                    ValidationVO validationVO = ValidationVO.create(item);
                    if (validate(validationVO)) {
                        NewsVO newsVO = NewsVO.create(item, newSite);
                        DgapData dgapData = fillDgapData(newsVO);
                        item.setBiaoShi(SysConst.ESDataStatus.EXISTS_IN_ES.getCode());
                        addKingBaseData(historyList, dgapData);
                        dgapData.setId(String.valueOf(item.getId()));
                        saveList.add(dgapData);
                        STATIC_LIST.add(dgapData);
                    }
                }
                if (SysConst.ESDataStatus.NOT_EXISTS_IN_ES.getCode().equals(item.getBiaoShi())) {
                    item.setBiaoShi(SysConst.ESDataStatus.EXCEPTION.getCode());
                }
                readList.add(item);
            }
        }

        if (saveList.size() > 0) {
            saveToFile(saveList, SysConst.VIDEO, analysisConfig.getSourceLessPath());
            saveToKingBase(historyList);
        }
        if (readList.size() > 0) {
            videoService.save(readList);
        }

        logger.info("视频分析,入库 {} 条", saveList.size());
        logger.info("视频分析,分析 {} 条", readList.size());

        recordNum(newIndexMap);
    }

    @Override
    protected boolean validate(ValidationVO validationVO) {
        String publishDate = validationVO.getFldrecddate();
        if (StringUtils.isEmpty(validationVO.getFldUrlAddr())
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

        result.setPublishType(SysConst.PublishType.VIDEO.getCode());
        result.setPolicyInfoType(3L);
        result.setTime(newsVO.getFabushijian());
        result.setHotEventMark(SysConst.HotEventMark.NOT_HOT_EVENT.getCode());
        result.setHitNum(0L);
        if (StringUtils.isNotEmpty(newsVO.getBofangshu())) {
            long count = Long.parseLong(newsVO.getBofangshu());
            if (count > SysConst.HOT_EVENT_THRESHOLD) {
                result.setHotEventMark(SysConst.HotEventMark.HOT_EVENT.getCode());
                result.setHitNum(count);
            }
        }
        if ("中华人民共和国中央人民政府".equals(newsVO.getWebname())) {
            result.setHotEventMark(SysConst.HotEventMark.HOT_EVENT.getCode());
            result.setPublishType(SysConst.PublishType.CENTER.getCode());
        }
        // 阅读量
        result.setReadNum(0L);
        result.setPolicyImageUrl(newsVO.getUrl());

        return result;
    }

}
