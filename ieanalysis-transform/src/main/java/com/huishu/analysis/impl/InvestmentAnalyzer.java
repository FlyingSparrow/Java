package com.huishu.analysis.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huishu.config.AnalysisConfig;
import com.huishu.config.UnitsConfig;
import com.huishu.constants.SysConst;
import com.huishu.entity.CityLib;
import com.huishu.entity.InvestmentDataBak;
import com.huishu.entity.KingBaseDgap;
import com.huishu.service.InvestmentDataBakService;
import com.huishu.vo.DgapData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 分析投资数据
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("investmentAnalyzer")
public class InvestmentAnalyzer extends DefaultAnalyzer {

    @Autowired
    private InvestmentDataBakService investmentDataBakService;
    @Autowired
    private UnitsConfig unitsConfig;

    @Override
    public String getName() {
        return "投资";
    }

    @Override
    public boolean getMark() {
        return analysisConfig.isInvestmentMark();
    }

    @Override
    public String getType() {
        return SysConst.INVESTMENT;
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

        InvestmentDataBak entity = new InvestmentDataBak();
        entity.setId(Long.valueOf(newIndexMap.get(getType())));
        Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
        List<InvestmentDataBak> list = investmentDataBakService.findOneHundred(entity, pageable);

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
        List<InvestmentDataBak> readList = new ArrayList<InvestmentDataBak>();
        List<KingBaseDgap> historyList = new ArrayList<KingBaseDgap>();
        for (InvestmentDataBak item : list) {
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
            investmentDataBakService.save(readList);
        }

        logger.info("{}分析,入库 {} 条", getName(), saveList.size());
        logger.info("{}分析,分析 {} 条", getName(), readList.size());

        recordNum(newIndexMap);
    }

    /**
     * 分析数据
     *
     * @param analysisConfig
     * @param indexMap
     */
    @Override
    protected void analysisData(AnalysisConfig analysisConfig, Map<String, String> indexMap) {
        Map<String, String> newIndexMap = new HashMap<>(indexMap);

        int pageNumber = 0;
        int totalPages = 10;
        InvestmentDataBak entity = new InvestmentDataBak();
        while (pageNumber <= totalPages){
            try {
                entity.setId(Long.valueOf(newIndexMap.get(getType())));
                Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
                Page<InvestmentDataBak> page = investmentDataBakService.findByPage(entity, pageable);
                totalPages = page.getTotalPages();

                List<InvestmentDataBak> list = page.getContent();
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
                    List<InvestmentDataBak> readList = new ArrayList<InvestmentDataBak>();
                    List<KingBaseDgap> historyList = new ArrayList<KingBaseDgap>();
                    for (InvestmentDataBak item : list) {
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
                        investmentDataBakService.save(readList);
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
                    Thread.sleep(300000);
                }
            } catch (Exception e) {
                logger.error("第 {} 页的{}数据分析出错", pageNumber, getName(), e);
            }
        }
    }

    private DgapData fillDgapData(InvestmentDataBak investmentDataBak) {
        // 金额
        Double amount = com.huishu.utils.StringUtils.transformAmount(unitsConfig, investmentDataBak.getAmount());
        if (amount == null) {
            return null;
        }

        DgapData result = new DgapData();

        // 省份
        fillAreaAndProvinceInfo(result, investmentDataBak.getRegion());
        if (StringUtils.isEmpty(result.getProvince())) {
            return null;
        }
        String companyName = getCompanyName(investmentDataBak.getInvestor());
        if(StringUtils.isEmpty(companyName)){
            return null;
        }
        // 时间
        fillDateInfoOfDgapData(result, investmentDataBak.getTime());
        result.setDataType(SysConst.DataType.INVESTMENT.getCode());
        // 行业
        result.setIndustry(investmentDataBak.getIndustry().trim());
        result.setPublishType(SysConst.PublishType.INVESTMENT.getCode());
        result.setFinancingAmount(amount);
        result.setQuitAmount(0d);
        result.setMergersAmount(0d);
        result.setCompanyName(companyName);
        result.setPolicyUrl(investmentDataBak.getFldUrlAddr());

        return result;
    }

    private String getCompanyName(String investor) {
        String result = investor;

        try {
            if (StringUtils.isEmpty(investor)) {
                return result;
            }

            //判断 investor 不是JSON数组
            if(!investor.trim().startsWith("[")){
                logger.info("investor: {}", investor);
                return result;
            }

            JSONArray parseArray = JSON.parseArray(investor);
            if (parseArray.size() == 1) {
                JSONObject jsonObject = (JSONObject) parseArray.get(0);
                return jsonObject.getString("shortCnName");
            }
        } catch (Exception e) {
            logger.error("解析公司名称出错，investor： {}", investor, e);
        }
        return result;
    }

    private boolean validate(InvestmentDataBak investmentDataBak) {
        String time = investmentDataBak.getTime();
        String region = investmentDataBak.getRegion();
        if (StringUtils.isEmpty(investmentDataBak.getIndustry())
                || StringUtils.isEmpty(time)
                || StringUtils.isEmpty(region)
                || StringUtils.isEmpty(investmentDataBak.getAmount())) {
            return false;
        }

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
