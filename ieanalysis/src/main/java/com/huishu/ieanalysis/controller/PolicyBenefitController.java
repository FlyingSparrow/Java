package com.huishu.ieanalysis.controller;

import com.alibaba.fastjson.JSONObject;
import com.huishu.base.bean.AjaxResult;
import com.huishu.base.controller.BaseController;
import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.service.PolicyBenefitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: PolicyBenefitController</p>
 * <p>Description: </p>
 *
 * @author xiaobo
 * @date 2017年3月29日
 */
@RestController
@RequestMapping(value = "/policybenefit")
public class PolicyBenefitController extends BaseController {

    @Autowired
    private PolicyBenefitService policyBenefitService;

    /**
     * 政策效益---政策效益分析---各月投融资情况
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyBenefitEveryMonth.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyBenefitEveryMonth(ConditionDTO cond) {
        cond.setDataType(SysConst.DataType.INVESTMENT.getCode());
        JSONObject map = policyBenefitService.searchPolicyBenefitEveryMonth(cond);
        return success(map);
    }


    /**
     * 政策效益---政策效益分析---企业投资Top5
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyBenefItenterpriseInvestmentTopFive.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyBenefItenterpriseInvestmentTopFive(ConditionDTO cond) {
        cond.setDataType(SysConst.DataType.INVESTMENT.getCode());
        JSONObject map = policyBenefitService.searchPolicyBenefitEnterpriseInvestmentTopFive(cond);
        return success(map);
    }


    /**
     * 政策效益---政策效益分析---投资地区top5
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyBenefIndustryArea.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyBenefIndustryArea(ConditionDTO cond) {
        cond.setDataType(SysConst.DataType.INVESTMENT.getCode());
        JSONObject map = policyBenefitService.searchPolicyBenefIndustryArea(cond);
        return success(map);
    }


    /**
     * 政策效益---政策效益分析---投资行业Top5
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyBenefItenterpriseIndustryTopFive.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyBenefItenterpriseIndustryTopFive(ConditionDTO cond) {
        cond.setDataType(SysConst.DataType.INVESTMENT.getCode());
        JSONObject map = policyBenefitService.searchPolicyBenefItenterpriseIndustryTopFive(cond);
        return success(map);
    }


    /**
     * 政策效益---政策效益分析---公共服务秩序效率分析
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyBenefPublicServiceEfficiencyAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyBenefPublicServiceEfficiencyAnalysis(ConditionDTO cond) {
        JSONObject map = policyBenefitService.searchPolicyBenefPublicServiceEfficiencyAnalysis(cond);
        return success(map);
    }

}