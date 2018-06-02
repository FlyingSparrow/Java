package com.huishu.analysis.impl;

import com.huishu.analysis.vo.NewsVO;
import com.huishu.analysis.vo.ValidationVO;
import com.huishu.config.AnalysisConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.KingBaseDgap;
import com.huishu.entity.NewsLibBak;
import com.huishu.entity.SiteLib;
import com.huishu.service.NewsLibBakService;
import com.huishu.service.SiteLibService;
import com.huishu.vo.DgapData;
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
 * 分析新闻数据
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("newsAnalyzer")
public class NewsAnalyzer extends DefaultAnalyzer {

    private static final List<DgapData> STATIC_LIST = new ArrayList<DgapData>();

    @Autowired
    private NewsLibBakService newsLibService;
    @Autowired
    private SiteLibService siteLibService;

    @Override
    public String getName() {
        return SysConst.NEWS;
    }

    @Override
    public void analysis(AnalysisConfig analysisConfig, ThreadPoolExecutor executor, Map<String, String> indexMap) {
        STATIC_LIST.clear();
        if (analysisConfig.isNewsMark()) {
            for (int i = 0; i < analysisConfig.getNewsThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{}新闻数据分析开始", currentThread.getName(), currentThread.getId());
                    try {
                        analysisData(analysisConfig, indexMap, pageNumber);
                    } catch (Exception e) {
                        logger.error("新闻数据分析异常", e);
                    }
                    logger.info("{}:{}新闻数据分析结束", currentThread.getName(), currentThread.getId());
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
        NewsLibBak news = new NewsLibBak();
        news.setId(Long.valueOf(indexMap.get(SysConst.NEWS)));
        Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
        List<NewsLibBak> newsList = newsLibService.findOneHundred(news, pageable);

        logger.info("新闻分析,读取 {} 条", newsList.size());

        if (newsList.size() <= 0) {
            return;
        }

        String newId = newsList.get(newsList.size() - 1).getId() + "";
        String oldId = indexMap.get(SysConst.NEWS);
        Map<String, String> newIndexMap = new HashMap<>(indexMap);
        if (Long.parseLong(newId) > Long.parseLong(oldId)) {
            newIndexMap.put(SysConst.NEWS, newId);
        }

        List<DgapData> saveList = new ArrayList<DgapData>();
        List<KingBaseDgap> historyList = new ArrayList<KingBaseDgap>();
        List<NewsLibBak> readList = new ArrayList<NewsLibBak>();
        for (NewsLibBak item : newsList) {
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
            saveToFile(saveList, SysConst.NEWS, analysisConfig.getSourceMorePath());
            saveToKingBase(historyList);
        }
        if (readList.size() > 0) {
            newsLibService.save(readList);
        }

        logger.info("新闻分析,入库 {} 条", saveList.size());
        logger.info("新闻分析,分析 {} 条", readList.size());

        recordNum(newIndexMap);
    }

    @Override
    protected DgapData fillDgapData(NewsVO newsVO) {
        DgapData result = super.fillDgapData(newsVO);
        result.setPublishType(SysConst.PublishType.NEWS.getCode());

        return result;
    }

}
