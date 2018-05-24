package com.huishu.ieanalysis.es.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.repository.DgapDataRepository;
import com.huishu.ieanalysis.es.service.AbstractService;
import com.huishu.ieanalysis.es.service.PolicyIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangjianchun
 */
@Service
public class PolicyIndexServiceImpl extends AbstractService implements
        PolicyIndexService {

    private static final Logger logger = LoggerFactory
            .getLogger(PolicyHotServiceImpl.class);

    @Autowired
    private DgapDataRepository dgapDataRepository;

    @Override
    public JSONObject searchFinancingAmountAnalysis(ConditionDTO cond) {
        JSONObject map = new JSONObject();
        return map;
    }

    @Override
    public JSONObject searchMergersAmountAnalysis(ConditionDTO cond) {
        JSONObject map = new JSONObject();
        return map;
    }

    @Override
    public JSONObject searchQuitAmountAnalysis(ConditionDTO cond) {
        JSONObject map = new JSONObject();
        return map;
    }

    private Double[] searchCapitalIndexResult(ConditionDTO cond, String aggField) {
        Double[] amountArray = new Double[12];
        return amountArray;
    }

    @Override
    public JSONObject searchJobsNumberAnalysis(ConditionDTO cond) {
        JSONObject map = new JSONObject();
        return map;
    }

    @Override
    public JSONObject searchAvgRemunerationAnalysis(ConditionDTO cond) {
        JSONObject map = new JSONObject();
        return map;
    }

    @Override
    public JSONObject searchCoverHeatAnalysis(ConditionDTO cond) {
        JSONObject map = new JSONObject();
        return map;
    }

    @Override
    public JSONObject searchCombinationTalentRatioAnalysis(
            ConditionDTO cond) {
        JSONObject map = new JSONObject();
        return map;
    }

    @Override
    public JSONObject searchEnterpriseCoreCompetitivenessAnalysis(
            ConditionDTO cond) {
        JSONObject map = new JSONObject();
        return map;
    }

    @Override
    public JSONObject searchEnterpriseMarketForceAnalysis(
            ConditionDTO cond) {
        JSONObject map = new JSONObject();
        return map;
    }

    @Override
    public JSONObject searchEnterpriseInnovationAndCreativityAnalysis(
            ConditionDTO cond) {
        JSONObject map = new JSONObject();
        return map;
    }

    @Override
    public JSONObject searchManagementEnvironmentAnalysis(
            ConditionDTO cond) {
        JSONObject map = new JSONObject();
        return map;
    }

    @Override
    public JSONObject searchCentralPolicyPublishAnalysis(
            ConditionDTO cond) {
        JSONObject map = new JSONObject();
        return map;
    }

    @Override
    public JSONObject searchLocalPolicyPublishAnalysis(
            ConditionDTO cond) {
        JSONObject map = new JSONObject();
        return map;
    }

    @Override
    public JSONObject searchProtectionIntellectualLitigationAnalysis(
            ConditionDTO cond) {
        JSONObject map = new JSONObject();
        return map;
    }

    @Override
    public JSONObject searchPolicyReportAndFocusAnalysis(
            ConditionDTO cond) {
        JSONObject map = new JSONObject();
        return map;
    }

    @Override
    public JSONObject searchRegisterAndSurviveNumAnalysis(
            ConditionDTO cond) {
        JSONObject map = new JSONObject();
        return map;
    }

    @Override
    public JSONObject searchRegisterAndSurviveAmountAnalysis(
            ConditionDTO cond) {
        JSONObject map = new JSONObject();
        return map;
    }

}
