package com.huishu.analysis.impl;

import com.huishu.analysis.vo.NewsVO;
import com.huishu.analysis.vo.ValidationVO;
import com.huishu.config.AnalysisConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.ForumLibBak;
import com.huishu.entity.KingBaseDgap;
import com.huishu.entity.SiteLib;
import com.huishu.service.ForumLibBakService;
import com.huishu.service.SiteLibService;
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
 * 分析论坛数据
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("forumAnalyzer")
public class ForumAnalyzer extends DefaultAnalyzer {

    private static final List<DgapData> STATIC_LIST = new ArrayList<DgapData>();

    @Autowired
    private ForumLibBakService forumLibService;
    @Autowired
    private SiteLibService siteLibService;

    @Override
    public String getName() {
        return SysConst.NEWS;
    }

    @Override
    public void analysis(AnalysisConfig analysisConfig, ThreadPoolExecutor executor, Map<String, String> indexMap) {
        if (analysisConfig.isForumMark()) {
            for (int i = 0; i < analysisConfig.getForumThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{}论坛数据分析开始", currentThread.getName(), currentThread.getId());
                    try {
                        analysisData(analysisConfig, indexMap, pageNumber);
                    } catch (Exception e) {
                        logger.error("论坛数据分析异常", e);
                    }
                    logger.info("{}:{}论坛数据分析结束", currentThread.getName(), currentThread.getId());
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
        ForumLibBak forum = new ForumLibBak();
        forum.setId(Long.valueOf(indexMap.get(SysConst.FORUM)));
        Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
        List<ForumLibBak> lists = forumLibService.findOneHundred(forum, pageable);

        logger.info("论坛分析,读取 {} 条", lists.size());

        if (lists.size() <= 0) {
            return;
        }

        String newId = lists.get(lists.size() - 1).getId() + "";
        String oldId = indexMap.get(SysConst.FORUM);
        Map<String, String> newIndexMap = new HashMap<>(indexMap);
        if (Long.valueOf(newId) > Long.valueOf(oldId)) {
            newIndexMap.put(SysConst.FORUM, newId);
        }

        List<DgapData> saveList = new ArrayList<DgapData>();
        List<ForumLibBak> readList = new ArrayList<ForumLibBak>();
        List<KingBaseDgap> historyList = new ArrayList<KingBaseDgap>();
        for (ForumLibBak item : lists) {
            if (isNotExists(STATIC_LIST, item.getFldUrlAddr())) {
                // 分析
                SiteLib site = siteLibService.findByName(item.getWebname());
                SiteLib newSite = fillAreaInfoForSiteLib(item.getFldtitle(), item.getFldcontent(), site);
                if (newSite != null) {
                    ValidationVO validationVO = ValidationVO.create(item, newSite);
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
            saveToFile(saveList, SysConst.FORUM, analysisConfig.getSourceMorePath());
            saveToKingBase(historyList);
        }
        if (readList.size() > 0) {
            forumLibService.save(readList);
        }

        logger.info("论坛分析,入库 {} 条", saveList.size());
        logger.info("论坛分析,分析 {} 条", readList.size());

        recordNum(newIndexMap);
    }

    @Override
    protected boolean validate(ValidationVO validationVO) {
        String publishDate = validationVO.getFldrecddate();
        if (StringUtils.isEmpty(validationVO.getProvince())
                || StringUtils.isEmpty(validationVO.getIndustry())
                || StringUtils.isEmpty(validationVO.getFldcontent())
                || StringUtils.isEmpty(validationVO.getFldtitle())
                || StringUtils.isEmpty(publishDate)) {
            return false;
        }

        int yearIndex = publishDate.indexOf("-");
        if (yearIndex <= 0) {
            return false;
        }
        int monthIndex = publishDate.indexOf("-", yearIndex + 1);
        if (monthIndex <= 0) {
            return false;
        }

        return true;
    }

    @Override
    protected DgapData fillDgapData(NewsVO newsVO) {
        DgapData result = super.fillDgapData(newsVO);
        result.setPublishType(SysConst.PublishType.FORUM.getCode());
        result.setSocialChannel(SysConst.SocialChannel.FORUM.getCode());

        return result;
    }

}
