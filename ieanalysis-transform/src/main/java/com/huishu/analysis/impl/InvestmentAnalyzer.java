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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 分析投资数据
 *
 * @author wangjianchun
 * @create 2018/5/26
 */
@Component("investmentAnalyzer")
public class InvestmentAnalyzer extends DefaultAnalyzer {

    @Autowired
    private InvestmentDataBakService investmentDataService;
    @Autowired
    private UnitsConfig unitsConfig;

    @Override
    public String getName() {
        return SysConst.INVESTMENT;
    }

    @Override
    public void analysis(AnalysisConfig analysisConfig, ThreadPoolExecutor executor, Map<String, String> indexMap) {
        if (analysisConfig.isInvestmentMark()) {
            // 分析投资数据
            for (int i = 0; i < analysisConfig.getInvestmentThreadNum(); i++) {
                final int pageNumber = i;
                executor.execute(() -> {
                    Thread currentThread = Thread.currentThread();
                    logger.info("{}:{}投资数据分析开始", currentThread.getName(), currentThread.getId());
                    try {
                        analysisData(analysisConfig, indexMap, pageNumber);
                    } catch (Exception e) {
                        logger.error("投资数据分析异常", e);
                    }
                    logger.info("{}:{}投资数据分析结束", currentThread.getName(), currentThread.getId());
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
        InvestmentDataBak investmentDataBak = new InvestmentDataBak();
        investmentDataBak.setId(Long.valueOf(indexMap.get(SysConst.INVESTMENT)));
        Pageable pageable = new PageRequest(pageNumber, analysisConfig.getTransformNum());
        List<InvestmentDataBak> list = investmentDataService.findOneHundred(investmentDataBak, pageable);

        logger.info("投资分析,读取 {} 条", list.size());

        if (list.size() <= 0) {
            return;
        }

        String newId = list.get(list.size() - 1).getId() + "";
        String oldId = indexMap.get(SysConst.INVESTMENT);
        Map<String, String> newIndexMap = new HashMap<>(indexMap);
        if (Long.parseLong(newId) > Long.parseLong(oldId)) {
            newIndexMap.put(SysConst.INVESTMENT, newId);
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
            saveToFile(saveList, SysConst.INVESTMENT, analysisConfig.getSourceLessPath());
            saveToKingBase(historyList);
        }
        if (readList.size() > 0) {
            investmentDataService.save(readList);
        }

        logger.info("投资分析,入库 {} 条", saveList.size());
        logger.info("投资分析,分析 {} 条", readList.size());

        recordNum(newIndexMap);
    }

    private DgapData fillDgapData(InvestmentDataBak investmentDataBak) {
        // 金额
        Double amount = com.huishu.utils.StringUtils.transformAmount(unitsConfig, investmentDataBak.getAmount());
        if (amount == null) {
            return null;
        }

        DgapData result = new DgapData();

        result.setDataType(SysConst.DataType.INVESTMENT.getCode());

        // 时间
        fillDateInfoOfDgapData(result, investmentDataBak.getTime());
        // 行业
        result.setIndustry(investmentDataBak.getIndustry().trim());

        // 省份
        String province = getProvince(investmentDataBak.getRegion());
        result.setProvince(province);
        fillAreaInfo(result, investmentDataBak.getRegion());

        result.setPublishType(SysConst.PublishType.INVESTMENT.getCode());
        result.setFinancingAmount(amount);
        result.setQuitAmount(0d);
        result.setMergersAmount(0d);
        String companyName = getCompanyName(investmentDataBak.getInvestor());
        result.setCompanyName(companyName);
        result.setPolicyUrl(investmentDataBak.getFldUrlAddr());

        return result;
    }

    private String getCompanyName(String investor) {
        String result = investor;

        try {
            if (StringUtils.isNotEmpty(investor)) {
                JSONArray parseArray = JSON.parseArray(investor);
                if (parseArray.size() == 1) {
                    JSONObject jsonObject = (JSONObject) parseArray.get(0);
                    return jsonObject.getString("shortCnName");
                }
            }
        } catch (Exception e) {
            logger.error("解析公司名称出错", e);
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

        int yearIndex = time.indexOf("-");
        if (yearIndex <= 0) {
            return false;
        }

        int monthIndex = time.indexOf("-", yearIndex + 1);
        if (monthIndex <= 0) {
            return false;
        }

        if (StringUtils.isEmpty(getProvince(region))) {
            return false;
        }

        String companyName = getCompanyName(investmentDataBak.getInvestor());
        if (StringUtils.isEmpty(companyName)) {
            return false;
        }

        return true;
    }

    private void fillAreaInfo(DgapData dgapData, String region) {
        String city = com.huishu.utils.StringUtils.getCity(region);
        List<CityLib> cityList = cityLibService.findByCity(city);
        if (cityList != null && cityList.size() > 0) {
            dgapData.setArea(city);
        }
    }

    private String getProvince(String region) {
        String result = null;
        Set<String> provinceSet = SysConst.getProvinceSet();
        for (String province : provinceSet) {
            if (region.contains(province)) {
                result = province;
                break;
            }
        }
        if (StringUtils.isNotEmpty(result)) {
            return null;
        }

        String city = com.huishu.utils.StringUtils.getCity(region);
        if (StringUtils.isEmpty(city)) {
            return null;
        }

        List<CityLib> cityList = cityLibService.findByCity(city);
        if (cityList != null && cityList.size() > 0) {
            result = cityList.get(0).getProvince();
        }

        return result;
    }

}
