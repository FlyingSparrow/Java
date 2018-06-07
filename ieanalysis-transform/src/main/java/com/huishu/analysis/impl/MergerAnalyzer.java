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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 分析投资并购数据
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("mergerAnalyzer")
public class MergerAnalyzer extends DefaultAnalyzer {

    @Autowired
    private MergerDataBakService mergerDataBakService;
    @Autowired
    private UnitsConfig unitsConfig;

    @Override
    public String getName() {
        return "投资并购";
    }

    @Override
    public boolean getMark() {
        return analysisConfig.isMergerMark();
    }

    @Override
    public String getType() {
        return SysConst.MERGER;
    }

    /**
     * 分析数据
     *
     * @param analysisConfig
     * @param indexMap
     * @param pageNumber
     */
    @Override
    protected void analysisData(AnalysisConfig analysisConfig, Map<String, String> indexMap, int pageNumber) {
        Map<String, String> newIndexMap = new HashMap<>(indexMap);

        MergerDataBak entity = new MergerDataBak();
        entity.setId(Long.valueOf(newIndexMap.get(getType())));
        Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
        List<MergerDataBak> list = mergerDataBakService.findOneHundred(entity, pageable);

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
            saveToFile(saveList, getType(), analysisConfig.getSourceLessPath());
            saveToKingBase(historyList);
        }
        if (readList.size() > 0) {
            mergerDataBakService.save(readList);
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
        Map<String, String> newIndexMap = new HashMap<>(indexMap);

        int pageNumber = 0;
        int totalPages = 10;
        MergerDataBak entity = new MergerDataBak();
        while (pageNumber <= totalPages){
            try {
                entity.setId(Long.valueOf(newIndexMap.get(getType())));
                Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
                Page<MergerDataBak> page = mergerDataBakService.findByPage(entity, pageable);
                totalPages = page.getTotalPages();

                List<MergerDataBak> list = page.getContent();
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
                        saveToFile(saveList, getType(), analysisConfig.getSourceLessPath());
                        saveToKingBase(historyList);
                    }
                    if (readList.size() > 0) {
                        mergerDataBakService.save(readList);
                    }

                    logger.info("{}分析,入库 {} 条", getName(), saveList.size());
                    logger.info("{}分析,分析 {} 条", getName(), readList.size());

                    recordNum(newIndexMap);

                    logger.info("第 {} 页{}数据分析结束", pageNumber, getName());

                    pageNumber++;
                }else{
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
