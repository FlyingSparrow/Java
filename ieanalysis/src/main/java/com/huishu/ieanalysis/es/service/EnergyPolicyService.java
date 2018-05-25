package com.huishu.ieanalysis.es.service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.dto.ConditionDTO;

/**
 * @author wangjianchun
 */
public interface EnergyPolicyService {

    /**
     * 投资分析--区域投资总额及投资增长率
     * @param cond
     * @return
     */
    JSONObject searchInvestmentAmountAndGrowthRate(ConditionDTO cond);

    /**
     * 投资分析--热门投资投资方向TOP5（全年）
     * @param cond
     * @return
     */
    JSONObject searchHotInvestmentIndustryTop(ConditionDTO cond);

    /**
     * 投资分析--区域投资方向分布
     * @param cond
     * @return
     */
    JSONObject searchInvestmentIndustryDistribute(ConditionDTO cond);

    /**
     * 注册量分析--区域新企业注册增长比对
     * @param cond
     * @return
     */
    JSONObject searchRegistrationsRateContrast(ConditionDTO cond);

    /**
     * 注册量分析--区域新企业注册量比对
     * @param cond
     * @return
     */
    JSONObject searchRegistrationsContrast(ConditionDTO cond);

    /**
     * 注册效率分析--新企业注册时间分布
     * @param cond
     * @return
     */
    JSONObject searchNewEnterpriseRegistrationTimeDistribution(
            ConditionDTO cond);

    /**
     * 注册效率分析--新企业注册时间与满意度统计
     * @param cond
     * @return
     */
    JSONObject searchNewEnterpriseRegistrationTimeAndSatisfactionStatistics(
            ConditionDTO cond);

    /**
     * 注册效率分析--新企业注册时间同期比对
     * @param cond
     * @return
     */
    JSONObject searchNewEnterpriseRegistrationThanSamePeriod(
            ConditionDTO cond);
}