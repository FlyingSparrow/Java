package com.huishu.ieanalysis.controller;

import com.alibaba.fastjson.JSONObject;
import com.huishu.base.bean.AjaxResult;
import com.huishu.base.controller.BaseController;
import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.service.PolicyIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>Title: PolicyIndexController</p>
 * <p>Description: </p>
 * @author xiaobo
 * @date 2017年3月27日
 */
@RestController
@RequestMapping(value = "/policyindex")
public class PolicyIndexController extends BaseController {
	
	@Autowired
	private PolicyIndexService policyIndexService;

	private static final Logger logger = LoggerFactory.getLogger(PolicyIndexController.class);
	

	public void setDefaultYear(ConditionDTO cond){
		 
	}
	
	/**
	 * 资本指数分析--融资金额分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchFinancingAmountAnalysis.json")
	public AjaxResult searchFinancingAmountAnalysis(ConditionDTO cond) {
		try {
			setDefaultYear(cond);
			cond.setDataType(SysConst.DATATYPE_INVESTMENT);
			List<String> publish=new ArrayList<String>();
			publish.add("9");
			cond.setPublishType(publish);
			JSONObject map = policyIndexService.searchFinancingAmountAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("资本指数分析--融资金额分析-异常：{} ",  e);
		}
		return fail("资本指数分析--查询融资金额分析失败，请重试");
	}
	
	/**
	 * 资本指数分析--并购金额分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchMergersAmountAnalysis.json")
	public AjaxResult searchMergersAmountAnalysis(ConditionDTO cond) {
		try {
			setDefaultYear(cond);
			cond.setDataType(SysConst.DATATYPE_INVESTMENT);
			List<String> publish=new ArrayList<String>();
			publish.add("13");
			cond.setPublishType(publish);
			JSONObject map = policyIndexService.searchMergersAmountAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("资本指数分析--并购金额分析-异常：{} ",  e);
		}
		return fail("资本指数分析--查询并购金额分析失败，请重试");
	}
	
	/**
	 * 资本指数分析--退出金额分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchQuitAmountAnalysis.json")
	public AjaxResult searchQuitAmountAnalysis(ConditionDTO cond) {
		try {
			setDefaultYear(cond);
			cond.setDataType(SysConst.DATATYPE_INVESTMENT);
			List<String> publish=new ArrayList<String>();
			publish.add("14");
			cond.setPublishType(publish);
			JSONObject map = policyIndexService.searchQuitAmountAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("资本指数分析--退出金额分析-异常：{} ",  e);
		}
		return fail("资本指数分析--查询退出金额分析失败，请重试");
	}
	
	
	/**
	 * 人才指数分析--网络招聘岗位数分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchJobsNumberAnalysis.json")
	public AjaxResult searchJobsNumberAnalysis(ConditionDTO cond) {
		try {
			setDefaultYear(cond);
			cond.setDataType(SysConst.DATATYPE_RECRUITMENT);
			JSONObject map = policyIndexService.searchJobsNumberAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("人才指数分析--网络招聘岗位数分析-异常：{} ",  e);
		}
		return fail("人才指数分析--查询网络招聘岗位数分析失败，请重试");
	}
	
	/**
	 * 人才指数分析--网络招聘平均薪酬分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchAvgRemunerationAnalysis.json")
	public AjaxResult searchAvgRemunerationAnalysis(ConditionDTO cond) {
		try {
			setDefaultYear(cond);
			cond.setDataType(SysConst.DATATYPE_RECRUITMENT);
			JSONObject map = policyIndexService.searchAvgRemunerationAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("人才指数分析--网络招聘平均薪酬分析-异常：{} ",  e);
		}
		return fail("人才指数分析--查询网络招聘平均薪酬分析失败，请重试");
	}
	
	/**
	 * 人才指数分析--求职热度分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchCoverHeatAnalysis.json")
	public AjaxResult searchCoverHeatAnalysis(ConditionDTO cond) {
		try {
			JSONObject map = policyIndexService.searchCoverHeatAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("人才指数分析--求职热度分析分析-异常：{} ",  e);
		}
		return fail("人才指数分析--查询求职热度分析失败，请重试");
	}
	
	/**
	 * 人才指数分析--双高人才比例分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchCombinationTalentRatioAnalysis.json")
	public AjaxResult searchCombinationTalentRatioAnalysis(ConditionDTO cond) {
		try {
			setDefaultYear(cond);
			cond.setDataType(SysConst.DATATYPE_RECRUITMENT);
			JSONObject map = policyIndexService.searchCombinationTalentRatioAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("人才指数分析--双高人才比例分析-异常：{} ",  e);
		}
		return fail("人才指数分析--查询双高人才比例分析失败，请重试");
	}
	
	
	/**
	 * 健康度指数分析--企业核心竞争力分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchEnterpriseCoreCompetitivenessAnalysis.json")
	public AjaxResult searchEnterpriseCoreCompetitivenessAnalysis(ConditionDTO cond) {
		try {
			JSONObject map = policyIndexService.searchEnterpriseCoreCompetitivenessAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("健康度指数分析--企业核心竞争力分析-异常：{} ",  e);
		}
		return fail("健康度指数分析--查询企业核心竞争力分析失败，请重试");
	}
	
	/**
	 * 健康度指数分析--企业市场力分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchEnterpriseMarketForceAnalysis.json")
	public AjaxResult searchEnterpriseMarketForceAnalysis(ConditionDTO cond) {
		try {
			JSONObject map = policyIndexService.searchEnterpriseMarketForceAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("健康度指数分析--企业市场力分析-异常：{} ",  e);
		}
		return fail("健康度指数分析--查询企业市场力分析失败，请重试");
	}
	
	/**
	 * 健康度指数分析--企业创新创造力
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchEnterpriseInnovationAndCreativityAnalysis.json")
	public AjaxResult searchEnterpriseInnovationAndCreativityAnalysis(ConditionDTO cond) {
		try {
			JSONObject map = policyIndexService.searchEnterpriseInnovationAndCreativityAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("健康度指数分析--企业创新创造力-异常：{} ",  e);
		}
		return fail("健康度指数分析--查询企业创新创造力失败，请重试");
	}
	
	/**
	 * 经营环境指数分析--经营环境指数分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchManagementEnvironmentAnalysis.json")
	public AjaxResult searchManagementEnvironmentAnalysis(ConditionDTO cond) {
		try {
			setDefaultYear(cond);
			List<String> list=new ArrayList<String>();
			list.add(SysConst.PUBLISHTYPE_CENTER);
			list.add(SysConst.PUBLISHTYPE_LOCAL);
 			cond.setPublishType(list);
 			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyIndexService.searchManagementEnvironmentAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("经营环境指数分析--经营环境指数分析异常：{} ",  e);
		}
		return fail("经营环境指数分析--经营环境指数分析失败，请重试");
	}
	
	/**
	 * 经营环境指数分析--中央政策发布数量
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchCentralPolicyPublishAnalysis.json")
	public AjaxResult searchCentralPolicyPublishAnalysis(ConditionDTO cond) {
		try {
			cond.setDataType(SysConst.DATATYPE_POLICY);
			setDefaultYear(cond);
			List<String> list=new ArrayList<String>();
			list.add(SysConst.PUBLISHTYPE_CENTER);
 			cond.setPublishType(list);
			JSONObject map = policyIndexService.searchCentralPolicyPublishAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("经营环境指数分析--中央政策发布数量异常：{} ",  e);
		}
		return fail("经营环境指数分析--中央政策发布数量失败，请重试");
	}
	
	/**
	 * 经营环境指数分析--地方政策发布数量
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchLocalPolicyPublishAnalysis.json")
	public AjaxResult searchLocalPolicyPublishAnalysis(ConditionDTO cond) {
		try {
			cond.setDataType(SysConst.DATATYPE_POLICY);
			setDefaultYear(cond);
			List<String> list=new ArrayList<String>();
			list.add(SysConst.PUBLISHTYPE_LOCAL);
 			cond.setPublishType(list);
			JSONObject map = policyIndexService.searchLocalPolicyPublishAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("经营环境指数分析--地方政策发布数量异常：{} ",  e);
		}
		return fail("经营环境指数分析--地方政策发布数量失败，请重试");
	}
	
	/**
	 * 经营环境指数分析--知识产权保护诉讼数量
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchProtectionIntellectualLitigationAnalysis.json")
	public AjaxResult searchProtectionIntellectualLitigationAnalysis(ConditionDTO cond) {
		try {
			JSONObject map = policyIndexService.searchProtectionIntellectualLitigationAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("经营环境指数分析--知识产权保护诉讼数量异常：{} ",  e);
		}
		return fail("经营环境指数分析--知识产权保护诉讼数量失败，请重试");
	}
	/**
	 * 经营环境指数分析--报道数/关注度
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchPolicyReportAndFocusAnalysis.json")
	public AjaxResult searchPolicyReportAndFocusAnalysis(ConditionDTO cond) {
		try {
			List<String> list=new ArrayList<String>();
			list.add(SysConst.PUBLISHTYPE_CENTER);
			list.add(SysConst.PUBLISHTYPE_LOCAL);
 			cond.setPublishType(list);
			cond.setDataType(SysConst.DATATYPE_POLICY);
			setDefaultYear(cond);
			JSONObject map = policyIndexService.searchPolicyReportAndFocusAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("经营环境指数分析--报道数/关注度异常：{} ",  e);
		}
		return fail("经营环境指数分析--报道数/关注度失败，请重试");
	}
	
	/**
	 * 活跃度指数分析--注册企业数量--环比增长
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchRegisterAndSurviveNumAnalysis.json")
	public AjaxResult searchRegisterAndSurviveNumAnalysis(ConditionDTO cond) {
		try {
			JSONObject map = policyIndexService.searchRegisterAndSurviveNumAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("活跃度指数分析--注册企业数量--环比增长异常：{} ",  e);
		}
		return fail("活跃度指数分析-注册企业数量--环比增长失败，请重试");
	}
	/**
	 * 活跃度指数分析--注册企业资金总额
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月21日
	 */
	@RequestMapping("/searchRegisterAndSurviveAmountAnalysis.json")
	public AjaxResult searchRegisterAndSurviveAmountAnalysis(ConditionDTO cond) {
		try {
			JSONObject map = policyIndexService.searchRegisterAndSurviveAmountAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("活跃度指数分析--注册企业资金总额异常：{} ",  e);
		}
		return fail("活跃度指数分析--注册企业资金总额失败，请重试");
	}
	
	 
}