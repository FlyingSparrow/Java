package com.huishu.ieanalysis.es.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.service.AbstractService;
import com.huishu.ieanalysis.es.service.PolicyBenefitService;
import org.springframework.stereotype.Service;

/**
 * @author wangjianchun
 */
@Service
public class PolicyBenefitServiceImpl extends AbstractService implements
        PolicyBenefitService {

	@Override
	public JSONObject searchPolicyBenefitEveryMonth(ConditionDTO cond) {
		JSONObject map = new JSONObject();
		return map;
	}

	@Override
	public JSONObject searchPolicyBenefItenterpriseInvestmentTopFive(
			ConditionDTO cond) {
		JSONObject map = new JSONObject();
		return map;
	}

	@Override
	public JSONObject searchPolicyBenefIndustryArea(ConditionDTO cond) {
		JSONObject map = new JSONObject();
		return map;
	}

	@Override
	public JSONObject searchPolicyBenefItenterpriseIndustryTopFive(
			ConditionDTO cond) {
		JSONObject map = new JSONObject();
		return map;
	}

	@Override
	public JSONObject searchPolicyBenefPublicServiceEfficiencyAnalysis(
			ConditionDTO cond) {
		JSONObject map = new JSONObject();
		return map;
	}

}
