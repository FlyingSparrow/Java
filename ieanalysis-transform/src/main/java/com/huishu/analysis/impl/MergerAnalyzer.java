package com.huishu.analysis.impl;

import com.huishu.config.AnalysisConfig;
import com.huishu.config.UnitsConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.CityLib;
import com.huishu.entity.KingBaseDgap;
import com.huishu.entity.MergerDataBak;
import com.huishu.service.MergerDataBakService;
import com.huishu.vo.DgapData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 分析投资并购数据
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("mergerAnalyzer")
public class MergerAnalyzer extends DefaultAnalyzer {

    @Autowired
    private MergerDataBakService mergerDataService;
    @Autowired
    private UnitsConfig unitsConfig;

    @Override
    public String getName() {
        return SysConst.MERGER;
    }

    @Override
    public void analysis(AnalysisConfig analysisConfig, ThreadPoolExecutor executor, Map<String, String> indexMap) {
        if (analysisConfig.isMergerMark()) {
            // 分析并购数据
            for (int i = 0; i < analysisConfig.getMergerThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{}投资并购数据分析开始", currentThread.getName(), currentThread.getId());
                    try {
                        analysisData(analysisConfig, indexMap, pageNumber);
                    } catch (Exception e) {
                        logger.error("投资并购数据分析异常", e);
                    }
                    logger.info("{}:{}投资并购数据分析结束", currentThread.getName(), currentThread.getId());
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
        MergerDataBak entity = new MergerDataBak();
        entity.setId(Long.valueOf(indexMap.get(SysConst.MERGER)));
        Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
        List<MergerDataBak> list = mergerDataService.findOneHundred(entity, pageable);

        logger.info("并购分析,读取 {} 条", list.size());

        if (list.size() <= 0) {
            return;
        }

        String newId = list.get(list.size() - 1).getId() + "";
        String oldId = indexMap.get(SysConst.MERGER);
        Map<String, String> newIndexMap = new HashMap<>(indexMap);
        if (Long.parseLong(newId) > Long.parseLong(oldId)) {
            newIndexMap.put(SysConst.MERGER, newId);
        }


        List<DgapData> saveList = new ArrayList<DgapData>();
        List<MergerDataBak> readList = new ArrayList<MergerDataBak>();
        List<KingBaseDgap> historyList = new ArrayList<KingBaseDgap>();
        for (MergerDataBak item : list) {
            // 分析
            if (validate(item)) {
                DgapData dgapData = fillDgapData(item);
                if (dgapData != null) {
                    item.setBiaoShi(SysConst.ESDataStatus.EXISTS_IN_ES.getCode());
                    addKingBaseData(historyList, dgapData);
                    dgapData.setId(String.valueOf(item.getId()));
                    saveList.add(dgapData);
                }
            }
            if (SysConst.ESDataStatus.NOT_EXISTS_IN_ES.getCode().equals(item.getBiaoShi())) {
                item.setBiaoShi(SysConst.ESDataStatus.EXCEPTION.getCode());
            }
            readList.add(item);
        }

        if (saveList.size() > 0) {
            saveToFile(saveList, SysConst.MERGER, analysisConfig.getSourceLessPath());
            saveToKingBase(historyList);
        }
        if (readList.size() > 0) {
            mergerDataService.save(readList);
        }

        logger.info("并购分析,入库 {} 条", saveList.size());
        logger.info("并购分析,分析 {} 条", readList.size());

        recordNum(newIndexMap);
    }

    private boolean validate(MergerDataBak mergerDataBak) {
        String time = mergerDataBak.getEndTime();
        if (StringUtils.isEmpty(mergerDataBak.getIndustry())
                || StringUtils.isEmpty(time)) {
            return false;
        }

        logger.info("time: {}", time);

        String tempTime = com.huishu.utils.StringUtils.transformTime(time);
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

    private DgapData fillDgapData(MergerDataBak mergerDataBak) {
        Double amount = com.huishu.utils.StringUtils.transformAmount(unitsConfig, mergerDataBak.getMergerAmount());
        if (amount == null) {
            return null;
        }

        DgapData result = new DgapData();

        // 省份
        fillAreaAndProvinceInfo(result, mergerDataBak.getAcquirerInfo());
        if (StringUtils.isEmpty(result.getProvince())) {
            return null;
        }
        // 时间
        fillDateInfoOfDgapData(result, mergerDataBak.getEndTime());
        result.setDataType(SysConst.DataType.INVESTMENT.getCode());
        // 行业
        result.setIndustry(mergerDataBak.getIndustry().trim());
        result.setPublishType(SysConst.PublishType.MERGER.getCode());
        // 金额
        result.setMergersAmount(amount);
        result.setCompanyName(mergerDataBak.getAcquirer());
        result.setQuitAmount(0D);
        result.setFinancingAmount(0D);
        result.setPolicyUrl(mergerDataBak.getFldUrlAddr());

        return result;
    }

    private void fillAreaAndProvinceInfo(DgapData dgapData, String region) {
        if (StringUtils.isEmpty(region)) {
            return;
        }

        Set<String> provinceSet = SysConst.getProvinceSet();
        for (String province : provinceSet) {
            if (region.contains(province)) {
                dgapData.setProvince(province);
                break;
            }
        }

        if (StringUtils.isNotEmpty(dgapData.getProvince())) {
            return;
        }

        String city = com.huishu.utils.StringUtils.getCity(region);
        if (StringUtils.isEmpty(city)) {
            return;
        }

        List<CityLib> cityList = cityLibService.findByCity(city);
        if (cityList != null && cityList.size() > 0) {
            dgapData.setProvince(cityList.get(0).getProvince());
            dgapData.setArea(city);
        }
    }

}
