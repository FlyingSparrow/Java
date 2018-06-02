package com.huishu.analysis.impl;

import com.huishu.analysis.vo.NewsVO;
import com.huishu.analysis.vo.ValidationVO;
import com.huishu.config.AnalysisConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.KingBaseDgap;
import com.huishu.entity.SiteLib;
import com.huishu.entity.ZongheBak;
import com.huishu.service.SiteLibService;
import com.huishu.service.ZongheBakService;
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
 * 分析综合数据
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("zongheAnalyzer")
public class ZongheAnalyzer extends DefaultAnalyzer {

    private static final List<DgapData> STATIC_LIST = new ArrayList<DgapData>();

    @Autowired
    private ZongheBakService zongheBakService;
    @Autowired
    private SiteLibService siteLibService;

    @Override
    public String getName() {
        return SysConst.ZONGHE;
    }

    @Override
    public void analysis(AnalysisConfig analysisConfig, ThreadPoolExecutor executor, Map<String, String> indexMap) {
        STATIC_LIST.clear();
        if (analysisConfig.isZongheMark()) {
            for (int i = 0; i < analysisConfig.getZongheThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{}综合数据分析开始", currentThread.getName(), currentThread.getId());
                    try {
                        analysisData(analysisConfig, indexMap, pageNumber);
                    } catch (Exception e) {
                        logger.error("综合数据分析异常", e);
                    }
                    logger.info("{}:{}综合数据分析结束", currentThread.getName(), currentThread.getId());
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
        ZongheBak news = new ZongheBak();
        news.setId(Long.valueOf(indexMap.get(SysConst.ZONGHE)));
        Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
        List<ZongheBak> newsList = zongheBakService.findOneHundred(news, pageable);

        logger.info("综合分析,读取 {} 条", newsList.size());

        if (newsList.size() > 0) {
            String newId = newsList.get(newsList.size() - 1).getId() + "";
            String oldId = indexMap.get(SysConst.ZONGHE);
            Map<String, String> newIndexMap = new HashMap<>(indexMap);
            if (Long.parseLong(newId) > Long.parseLong(oldId)) {
                newIndexMap.put(SysConst.NEWS, newId);
            }

            List<DgapData> saveList = new ArrayList<DgapData>();
            List<KingBaseDgap> historyList = new ArrayList<KingBaseDgap>();
            List<ZongheBak> readList = new ArrayList<ZongheBak>();
            for (ZongheBak item : newsList) {
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
                saveToFile(saveList, SysConst.ZONGHE, analysisConfig.getSourceMorePath());
                saveToKingBase(historyList);
            }
            if (readList.size() > 0) {
                zongheBakService.save(readList);
            }

            logger.info("综合分析,入库 {} 条", saveList.size());
            logger.info("综合分析,分析 {} 条", readList.size());

            recordNum(newIndexMap);
        }
    }

    @Override
    protected DgapData fillDgapData(NewsVO newsVO) {
        DgapData result = super.fillDgapData(newsVO);
        result.setPublishType(SysConst.PublishType.COMPREHENSIVE.getCode());

        return result;
    }

}
