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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private VideoBakService videoBakService;
    @Autowired
    private SiteLibService siteLibService;

    @Override
    public String getName() {
        return "视频";
    }

    @Override
    public boolean getMark() {
        return analysisConfig.isVideoMark();
    }

    @Override
    public String getType() {
        return SysConst.VIDEO;
    }

    /**
     * 分析数据
     * @param analysisConfig
     * @param indexMap
     */
    @Override
    protected void analysisData(AnalysisConfig analysisConfig, Map<String, String> indexMap, int pageNumber) {
        STATIC_LIST.clear();
        Map<String, String> newIndexMap = new HashMap<>(indexMap);

        VideoBak entity = new VideoBak();
        entity.setId(Long.valueOf(newIndexMap.get(getType())));
        Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
        List<VideoBak> list = videoBakService.findOneHundred(entity, pageable);

        logger.info("{}分析,读取 {} 条", getName(), list.size());

        if (list.size() <= 0) {
            return;
        }

        String newId = list.get(list.size() - 1).getId() + "";
        String oldId = newIndexMap.get(getType());
        if (Long.parseLong(newId) > Long.parseLong(oldId)) {
            newIndexMap.put(getType(), newId);
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
            saveToFile(saveList, getType(), analysisConfig.getSourceLessPath());
            saveToKingBase(historyList);
        }
        if (readList.size() > 0) {
            videoBakService.save(readList);
        }

        logger.info("{}分析,入库 {} 条", getName(), saveList.size());
        logger.info("{}分析,分析 {} 条", getName(), readList.size());

        recordNum(newIndexMap);
    }

    /**
     * 分析数据
     * @param analysisConfig
     * @param indexMap
     */
    @Override
    protected void analysisData(AnalysisConfig analysisConfig, Map<String, String> indexMap) {
        STATIC_LIST.clear();
        Map<String, String> newIndexMap = new HashMap<>(indexMap);

        int pageNumber = 0;
        int totalPages = 10;
        VideoBak entity = new VideoBak();
        while (pageNumber <= totalPages){
            try {
                entity.setId(Long.valueOf(newIndexMap.get(getType())));
                Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
                Page<VideoBak> page = videoBakService.findByPage(entity, pageable);
                totalPages = page.getTotalPages();

                List<VideoBak> list = page.getContent();
                if(list != null && list.size() > 0){
                    logger.info("{}分析,读取 {} 条", getName(), list.size());
                    logger.info("总页数：{}，每页记录数：{}，剩余 {} 条{}数据待分析", page.getTotalPages(),
                            analysisConfig.getTransformNum(), page.getTotalElements(), getName());
                    logger.info("第 {} 页{}数据分析开始", pageNumber, getName());

                    String newId = list.get(list.size() - 1).getId() + "";
                    String oldId = newIndexMap.get(getType());
                    if (Long.parseLong(newId) > Long.parseLong(oldId)) {
                        newIndexMap.put(getType(), newId);
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
                        saveToFile(saveList, getType(), analysisConfig.getSourceLessPath());
                        saveToKingBase(historyList);
                    }
                    if (readList.size() > 0) {
                        videoBakService.save(readList);
                    }

                    logger.info("{}分析,入库 {} 条", getName(), saveList.size());
                    logger.info("{}分析,分析 {} 条", getName(), readList.size());

                    recordNum(newIndexMap);

                    logger.info("第 {} 页{}数据分析结束", pageNumber, getName());

                    pageNumber++;
                }else {
                    pageNumber = 0;
                    totalPages = 10;
                    //如果没有数据需要分析，那么当前线程休眠5分钟
                    logger.info("没有{}数据需要分析，线程休眠 5 分钟", getName());
                    Thread.sleep(300000);
                }
            } catch (Exception e) {
                logger.error("第 {} 页的{}数据分析出错", pageNumber, getName(), e);
            }
        }
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
