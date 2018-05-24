package com.huishu.ieanalysis.es.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.service.AbstractService;
import com.huishu.ieanalysis.es.service.EnergyPolicyService;
import org.springframework.stereotype.Service;

/**
 * @author wangjianchun
 */
@Service
public class EnergyPolicyServiceImpl extends AbstractService implements EnergyPolicyService {

    @Override
    public JSONObject searchInvestmentAmountAndGrowthRate(
            ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchHotInvestmentIndustryTop(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchInvestmentIndustryDistribute(
            ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchRegistrationsContrast(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchRegistrationsRateContrast(ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchNewEnterpriseRegistrationTimeDistribution(
            ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchNewEnterpriseRegistrationTimeAndSatisfactionStatistics(
            ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject searchNewEnterpriseRegistrationThanSamePeriod(
            ConditionDTO cond) {
        JSONObject result = new JSONObject();
        return result;
    }

}
