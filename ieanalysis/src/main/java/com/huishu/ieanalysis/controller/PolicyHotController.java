package com.huishu.ieanalysis.controller;

import com.alibaba.fastjson.JSONObject;
import com.huishu.base.bean.AjaxResult;
import com.huishu.base.controller.BaseController;
import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.service.PolicyHotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * <p>Title: PolicyHotController</p>
 * <p>Description: </p>
 * @author xiaobo
 * @date 2017年3月29日
 */
@RestController
@RequestMapping(value = "/policyhot")
public class PolicyHotController extends BaseController {
	
	@Autowired
	private PolicyHotService policyHotService;

	private static final Logger logger = LoggerFactory.getLogger(PolicyHotController.class);
	
	private void setDefaultCond(ConditionDTO cond) {
		 
	}
 
	/**
	 * 政策热点---区域热点分析--区域双创政策关注度热力图
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyHotAreaFocusMapAnalysis.json")
	public AjaxResult searchPolicyHotAreaFocusMapAnalysis(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyHotService.searchPolicyHotAreaFocusMapAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策热点---区域热点分析--区域双创政策关注度热力图异常：{} ",  e);
		}
		return fail("政策热点---区域热点分析--区域双创政策关注度热力图失败，请重试");
	}
	
	/**
	 * 政策热点---区域热点分析--双创政策关注度地区排行
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyHotAreaFocusRankAnalysis.json")
	public AjaxResult searchPolicyHotAreaFocusRankAnalysis(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyHotService.searchPolicyHotAreaFocusRankAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策热点---区域热点分析--双创政策关注度地区排行异常：{} ",  e);
		}
		return fail("政策热点---区域热点分析--双创政策关注度地区排行失败，请重试");
	}
	
	/**
	 * 政策热点---区域热点分析--双创政策行业关注度
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyHotAreaFocusSocialSubjectAnalysis.json")
	public AjaxResult searchPolicyHotAreaFocusSocialSubjectAnalysis(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyHotService.searchPolicyHotAreaFocusSocialSubjectAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策热点---区域热点分析--双创政策行业关注度异常：{} ",  e);
		}
		return fail("政策热点---区域热点分析--双创政策行业关注度失败，请重试");
	}
	
	
	/**
	 * 政策热点---关注渠道分析--双创政策关注度媒体渠道分布
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyHotChannelSiteAnalysis.json")
	public AjaxResult searchPolicyHotChannelSiteAnalysis(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			cond.setVectorType("1");
			cond.setReportType(1L);
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyHotService.searchPolicyHotChannelSiteAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策热点---关注渠道分析--双创政策关注度媒体渠道分布异常：{} ",  e);
		}
		return fail("政策热点---关注渠道分析--双创政策关注度媒体渠道分布失败，请重试");
	}
	/**
	 * 政策热点---关注渠道分析--不同行业关注政策媒体渠道分布
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyHotChannelSocialSubjectAnalysis.json")
	public AjaxResult searchPolicyHotChannelSocialSubjectAnalysis(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			cond.setVectorType("1");
			cond.setReportType(1L);
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyHotService.searchPolicyHotChannelSocialSubjectAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策热点---关注渠道分析--不同社会主体关注政策媒体渠道分布异常：{} ",  e);
		}
		return fail("政策热点---关注渠道分析--不同社会主体关注政策媒体渠道分布失败，请重试");
	}
	
	
	/**
	 * 政策热点---媒体参与度分析--双创政策媒体渠道倾向性分布
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyHotMediaChannelEmotionAnalysis.json")
	public AjaxResult searchPolicyHotMediaChannelEmotionAnalysis(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			cond.setVectorType("1");
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyHotService.searchPolicyHotMediaChannelEmotionAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策热点---媒体参与度分析--双创政策媒体渠道倾向性分布异常：{} ",  e);
		}
		return fail("政策热点---媒体参与度分析--双创政策媒体渠道倾向性分布失败，请重试");
	}
	
	/**
	 * 政策热点---媒体参与度分析--各大媒体渠道政策参与度排行
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyHotMediaChannelRankAnalysis.json")
	public AjaxResult searchPolicyHotMediaChannelRankAnalysis(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			//1,媒体;2, 社会 ;3,行业;
			cond.setVectorType("1");
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyHotService.searchPolicyHotMediaChannelRankAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策热点---媒体参与度分析--各大媒体渠道政策参与度排行异常：{} ",  e);
		}
		return fail("政策热点---媒体参与度分析--各大媒体渠道政策参与度排行失败，请重试");
	}
	
	/**
	 * 政策热点---媒体参与度分析--各大媒体渠道倾向性分布统计
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyHotMediaChannelDistributeAnalysis.json")
	public AjaxResult searchPolicyHotMediaChannelDistributeAnalysis(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			//1,媒体;2, 社会 ;3,行业;
			cond.setVectorType("1");
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyHotService.searchPolicyHotMediaChannelDistributeAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策热点---媒体参与度分析--各大媒体渠道倾向性分布统计异常：{} ",  e);
		}
		return fail("政策热点---媒体参与度分析--各大媒体渠道倾向性分布统计失败，请重试");
	}
	
	
	/**
	 * 政策热点---社会参与度分析--双创政策社会渠道倾向性分布
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyHotSocialEmotionAnalysis.json")
	public AjaxResult searchPolicyHotSocialEmotionAnalysis(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			//1,媒体;2, 社会 ;3,行业;
			cond.setVectorType("2");
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyHotService.searchPolicyHotSocialEmotionAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策热点---社会参与度分析--双创政策社会渠道倾向性分布统计异常：{} ",  e);
		}
		return fail("政策热点--社会参与度分析--双创政策社会渠道倾向性分布失败，请重试");
	}
	
	/**
	 * 政策热点---社会参与度分析--社会渠道政策参与度排行
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyHotSocialChannelAnalysis.json")
	public AjaxResult searchPolicyHotSocialChannelAnalysis(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			//1,媒体;2, 社会 ;3,行业;
			cond.setVectorType("2");
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyHotService.searchPolicyHotSocialChannelAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策热点---社会参与度分析--社会渠道政策参与度排行统计异常：{} ",  e);
		}
		return fail("政策热点--社会参与度分析--社会渠道政策参与度排行失败，请重试");
	}
	
	/**
	 * 政策热点---社会参与度分析--各大社会渠道倾向性分布统计
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyHotSocialChannelDistributeAnalysis.json")
	public AjaxResult searchPolicyHotSocialChannelDistributeAnalysis(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			//1,媒体;2, 社会 ;3,行业;
			cond.setVectorType("2");
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyHotService.searchPolicyHotSocialChannelDistributeAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策热点---社会参与度分析--各大社会渠道倾向性分布统计异常：{} ",  e);
		}
		return fail("政策热点--社会参与度分析--各大社会渠道倾向性分布统计失败，请重试");
	}
	
	
	/**
	 * 政策热点---行业参与度分析--双创政策行业渠道倾向性分布
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyHotIndustryEmotionAnalysis.json")
	public AjaxResult searchPolicyHotIndustryEmotionAnalysis(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			//1,媒体;2, 社会 ;3,行业;
			cond.setVectorType("3");
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyHotService.searchPolicyHotIndustryEmotionAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策热点---行业参与度分析--双创政策行业渠道倾向性分布异常：{} ",  e);
		}
		return fail("政策热点--行业参与度分析--双创政策行业渠道倾向性分布失败，请重试");
	}
	
	
	/**
	 * 政策热点---行业参与度分析--政策参与度行业排行
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyHotIndustryRankAnalysis.json")
	public AjaxResult searchPolicyHotIndustryRankAnalysis(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			//1,媒体;2, 社会 ;3,行业;
			cond.setVectorType("3");
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyHotService.searchPolicyHotIndustryRankAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策热点---行业参与度分析--政策参与度行业排行异常：{} ",  e);
		}
		return fail("政策热点--行业参与度分析--政策参与度行业排行失败，请重试");
	}
	
	
	/**
	 * 政策热点---行业参与度分析--各大行业渠道倾向性分布统计
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyHotIndustryDistributeAnalysis.json")
	public AjaxResult searchPolicyHotIndustryDistributeAnalysis(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			//1,媒体;2, 社会 ;3,行业;
			cond.setVectorType("3");
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyHotService.searchPolicyHotIndustryDistributeAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策热点---行业参与度分析--各大行业渠道倾向性分布统计异常：{} ",  e);
		}
		return fail("政策热点--行业参与度分析--各大行业渠道倾向性分布统计失败，请重试");
	}
}