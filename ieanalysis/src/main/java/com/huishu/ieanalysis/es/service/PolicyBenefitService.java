package com.huishu.ieanalysis.es.service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.dto.ConditionDTO;

/**
 * @author wangjianchun
 */
public interface PolicyBenefitService {

    JSONObject searchPolicyBenefitEveryMonth(ConditionDTO cond);

    JSONObject searchPolicyBenefItenterpriseInvestmentTopFive(ConditionDTO cond);

    JSONObject searchPolicyBenefIndustryArea(ConditionDTO cond);

    JSONObject searchPolicyBenefItenterpriseIndustryTopFive(ConditionDTO cond);

    JSONObject searchPolicyBenefPublicServiceEfficiencyAnalysis(ConditionDTO cond);

}