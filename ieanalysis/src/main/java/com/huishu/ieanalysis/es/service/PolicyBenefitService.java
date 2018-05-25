package com.huishu.ieanalysis.es.service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.dto.ConditionDTO;

/**
 * @author wangjianchun
 */
public interface PolicyBenefitService {

    /**
     * 政策效益---政策效益分析---各月投融资情况
     * @param cond
     * @return
     */
    JSONObject searchPolicyBenefitEveryMonth(ConditionDTO cond);

    /**
     * 政策效益---政策效益分析---企业投资Top5
     * @param cond
     * @return
     */
    JSONObject searchPolicyBenefitEnterpriseInvestmentTopFive(ConditionDTO cond);

    /**
     * 政策效益---政策效益分析---投资地区top5
     * @param cond
     * @return
     */
    JSONObject searchPolicyBenefIndustryArea(ConditionDTO cond);

    /**
     * 政策效益---政策效益分析---投资行业Top5
     * @param cond
     * @return
     */
    JSONObject searchPolicyBenefItenterpriseIndustryTopFive(ConditionDTO cond);

    /**
     * 政策效益---政策效益分析---公共服务秩序效率分析
     * @param cond
     * @return
     */
    JSONObject searchPolicyBenefPublicServiceEfficiencyAnalysis(ConditionDTO cond);

}