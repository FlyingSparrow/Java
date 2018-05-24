package com.huishu.ieanalysis.es.service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.dto.ConditionDTO;

/**
 * @author wangjianchun
 */
public interface PolicyIndexService {

    JSONObject searchFinancingAmountAnalysis(ConditionDTO cond);

    JSONObject searchMergersAmountAnalysis(ConditionDTO cond);

    JSONObject searchQuitAmountAnalysis(ConditionDTO cond);

    JSONObject searchJobsNumberAnalysis(ConditionDTO cond);

    JSONObject searchAvgRemunerationAnalysis(ConditionDTO cond);

    JSONObject searchCoverHeatAnalysis(ConditionDTO cond);

    JSONObject searchCombinationTalentRatioAnalysis(ConditionDTO cond);

    JSONObject searchEnterpriseCoreCompetitivenessAnalysis(
            ConditionDTO cond);

    JSONObject searchEnterpriseMarketForceAnalysis(ConditionDTO cond);

    JSONObject searchEnterpriseInnovationAndCreativityAnalysis(
            ConditionDTO cond);

    JSONObject searchManagementEnvironmentAnalysis(ConditionDTO cond);

    JSONObject searchCentralPolicyPublishAnalysis(ConditionDTO cond);

    JSONObject searchLocalPolicyPublishAnalysis(ConditionDTO cond);

    JSONObject searchProtectionIntellectualLitigationAnalysis(
            ConditionDTO cond);

    JSONObject searchPolicyReportAndFocusAnalysis(ConditionDTO cond);

    JSONObject searchRegisterAndSurviveNumAnalysis(ConditionDTO cond);

    JSONObject searchRegisterAndSurviveAmountAnalysis(ConditionDTO cond);

}