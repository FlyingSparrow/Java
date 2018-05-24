package com.huishu.ieanalysis.es.service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.dto.ConditionDTO;

/**
 * @author wangjianchun
 */
public interface EnergyPolicyService {

    JSONObject searchInvestmentAmountAndGrowthRate(ConditionDTO cond);

    JSONObject searchHotInvestmentIndustryTop(ConditionDTO cond);

    JSONObject searchInvestmentIndustryDistribute(ConditionDTO cond);

    JSONObject searchRegistrationsRateContrast(ConditionDTO cond);

    JSONObject searchRegistrationsContrast(ConditionDTO cond);

    JSONObject searchNewEnterpriseRegistrationTimeDistribution(
            ConditionDTO cond);

    JSONObject searchNewEnterpriseRegistrationTimeAndSatisfactionStatistics(
            ConditionDTO cond);

    JSONObject searchNewEnterpriseRegistrationThanSamePeriod(
            ConditionDTO cond);
}