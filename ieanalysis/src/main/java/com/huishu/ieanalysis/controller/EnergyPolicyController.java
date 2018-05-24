package com.huishu.ieanalysis.controller;

import com.alibaba.fastjson.JSONObject;
import com.huishu.base.bean.AjaxResult;
import com.huishu.base.controller.BaseController;
import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.service.EnergyPolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * <p>Title: EnergyPolicyController</p>
 * <p>Description: </p>
 * @author xiaobo
 * @date 2017年3月21日
 */
@RestController
@RequestMapping(value = "/energyPolicy")
public class EnergyPolicyController extends BaseController {
	
	@Autowired
	private EnergyPolicyService energyPolicyService;

	private static final Logger logger = LoggerFactory.getLogger(EnergyPolicyController.class);
	

	public void setDefaultYear(ConditionDTO cond){
		 
	}
	
	/**
	 * 投资分析--区域投资总额及投资增长率
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchInvestmentAmountAndGrowthRate.json")
	public AjaxResult searchInvestmentAmountAndGrowthRate(ConditionDTO cond) {
		try {
			setDefaultYear(cond);
			cond.setDataType(SysConst.DATATYPE_INVESTMENT);
			JSONObject map = energyPolicyService.searchInvestmentAmountAndGrowthRate(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("投资分析--区域投资总额及投资增长率-异常：{} ",  e);
		}
		return fail("投资分析--查询区域投资总额及投资增长率失败，请重试");
	}
	
	/**
	 * 投资分析--热门投资投资方向TOP5（全年）
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchHotInvestmentIndustryTop.json")
	public AjaxResult searchHotInvestmentIndustryTop(ConditionDTO cond) {
		try {
			cond.setDataType(SysConst.DATATYPE_INVESTMENT);
			 JSONObject map =energyPolicyService.searchHotInvestmentIndustryTop(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("投资分析--热门投资投资方向TOP5-异常：{} ",  e);
		}
		return fail("投资分析--查询热门投资投资方向TOP5失败，请重试");
	}
	
	/**
	 * 投资分析--区域投资方向分布 
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchInvestmentIndustryDistribute.json")
	public AjaxResult searchInvestmentIndustryDistribute(ConditionDTO cond) {
		try {
			cond.setDataType(SysConst.DATATYPE_INVESTMENT);
			 JSONObject map =energyPolicyService.searchInvestmentIndustryDistribute(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("投资分析--区域投资方向分布 -异常：{} ",  e);
		}
		return fail("投资分析--查询区域投资方向分布 失败，请重试");
	}
	
	/**
	 * 注册量分析--区域新企业注册量比对 
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchRegistrationsContrast.json")
	public AjaxResult searchRegistrationsContrast(ConditionDTO cond) {
		try {
			 JSONObject map =energyPolicyService.searchRegistrationsContrast(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("注册量分析--区域新企业注册量比对  -异常：{} ",  e);
		}
		return fail("注册量分析--查询区域新企业注册量比对 失败，请重试");
	}
	/**
	 * 注册量分析--区域新企业注册增长比对
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchRegistrationsRateContrast.json")
	public AjaxResult searchRegistrationsRateContrast(ConditionDTO cond) {
		try {
			 JSONObject map =energyPolicyService.searchRegistrationsRateContrast(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("注册量分析--区域新企业注册增长比对 -异常：{} ",  e);
		}
		return fail("注册量分析--查询区域新企业注册增长比对失败，请重试");
	}
	
	
	/**
	 * 注册效率分析--新企业注册时间分布
	 * 
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchNewEnterpriseRegistrationTimeDistribution.json")
	public AjaxResult searchNewEnterpriseRegistrationTimeDistribution(ConditionDTO cond) {
		try {
			 JSONObject map =energyPolicyService.searchNewEnterpriseRegistrationTimeDistribution(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("注册效率分析--新企业注册时间分布 -异常：{} ",  e);
		}
		return fail("注册效率分析--查询新企业注册时间分布失败，请重试");
	}
	
	
	/**
	 * 注册效率分析--新企业注册时间与满意度统计
	 *  
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchNewEnterpriseRegistrationTimeAndSatisfactionStatistics.json")
	public AjaxResult searchNewEnterpriseRegistrationTimeAndSatisfactionStatistics(ConditionDTO cond) {
		try {
			 JSONObject map =energyPolicyService.searchNewEnterpriseRegistrationTimeAndSatisfactionStatistics(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("注册效率分析--新企业注册时间分布 -异常：{} ",  e);
		}
		return fail("注册效率分析--查询新企业注册时间分布失败，请重试");
	}
	
	/**
	 * 注册效率分析--新企业注册时间同期比对
	 *   
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchNewEnterpriseRegistrationThanSamePeriod.json")
	public AjaxResult searchNewEnterpriseRegistrationThanSamePeriod(ConditionDTO cond) {
		try {
			 setDefaultYear(cond);
			 cond.setDataType(SysConst.DATATYPE_INDUSTRY);
			 JSONObject map =energyPolicyService.searchNewEnterpriseRegistrationThanSamePeriod(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("注册效率分析--新企业注册时间分布 -异常：{} ",  e);
		}
		return fail("注册效率分析--查询新企业注册时间分布失败，请重试");
	}
	
	 
}