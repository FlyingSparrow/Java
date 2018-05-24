package com.huishu.ieanalysis.controller;

import com.alibaba.fastjson.JSONObject;
import com.huishu.base.bean.AjaxResult;
import com.huishu.base.controller.BaseController;
import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.service.PolicyBenefitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * <p>Title: PolicyBenefitController</p>
 * <p>Description: </p>
 * @author xiaobo
 * @date 2017年3月29日
 */
@RestController
@RequestMapping(value = "/policybenefit")
public class PolicyBenefitController extends BaseController {
	
	@Autowired
	private PolicyBenefitService policyBenefitService;

	private static final Logger logger = LoggerFactory.getLogger(PolicyBenefitController.class);
	
 
	/**
	 * 政策效益---政策效益分析---各月投融资情况
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyBenefitEveryMonth.json")
	public AjaxResult searchPolicyBenefitEveryMonth(ConditionDTO cond) {
		try {
			cond.setDataType(SysConst.DATATYPE_INVESTMENT);
			JSONObject map = policyBenefitService.searchPolicyBenefitEveryMonth(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策效益---政策效益分析---各月投融资情况异常：{} ",  e);
		}
		return fail("政策效益---政策效益分析---各月投融资情况失败，请重试");
	}
	
	
	/**
	 * 政策效益---政策效益分析---企业投资Top5
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyBenefItenterpriseInvestmentTopFive.json")
	public AjaxResult searchPolicyBenefItenterpriseInvestmentTopFive(ConditionDTO cond) {
		try {
			cond.setDataType(SysConst.DATATYPE_INVESTMENT);
			JSONObject map = policyBenefitService.searchPolicyBenefItenterpriseInvestmentTopFive(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策效益---政策效益分析---企业投资Top5异常：{} ",  e);
		}
		return fail("政策效益---政策效益分析---企业投资Top5失败，请重试");
	}
	
	
	/**
	 * 政策效益---政策效益分析---投资地区top5
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyBenefIndustryArea.json")
	public AjaxResult searchPolicyBenefIndustryArea(ConditionDTO cond) {
		try {
			cond.setDataType(SysConst.DATATYPE_INVESTMENT);
			JSONObject map = policyBenefitService.searchPolicyBenefIndustryArea(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策效益---政策效益分析---投资地区top5异常：{} ",  e);
		}
		return fail("政策效益---政策效益分析--投资地区top5况失败，请重试");
	}
	
	
	/**
	 * 政策效益---政策效益分析---投资行业Top5
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyBenefItenterpriseIndustryTopFive.json")
	public AjaxResult searchPolicyBenefItenterpriseIndustryTopFive(ConditionDTO cond) {
		try {
			cond.setDataType(SysConst.DATATYPE_INVESTMENT);
			JSONObject map = policyBenefitService.searchPolicyBenefItenterpriseIndustryTopFive(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策效益---政策效益分析---投资行业Top5异常：{} ",  e);
		}
		return fail("政策效益---政策效益分析--投资行业Top5失败，请重试");
	}
	
	
	/**
	 * 政策效益---政策效益分析---公共服务秩序效率分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyBenefPublicServiceEfficiencyAnalysis.json")
	public AjaxResult searchPolicyBenefPublicServiceEfficiencyAnalysis(ConditionDTO cond) {
		try {
			JSONObject map = policyBenefitService.searchPolicyBenefPublicServiceEfficiencyAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策效益---政策效益分析---公共服务秩序效率分析异常：{} ",  e);
		}
		return fail("政策效益---政策效益分析--公共服务秩序效率分析失败，请重试");
	}
 
}