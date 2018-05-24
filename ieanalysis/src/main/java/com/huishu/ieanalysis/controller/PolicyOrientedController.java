package com.huishu.ieanalysis.controller;

import com.alibaba.fastjson.JSONObject;
import com.huishu.base.bean.AjaxResult;
import com.huishu.base.controller.BaseController;
import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.service.PolicyOrientedService;
import com.huishu.ieanalysis.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * <p>Title: PolicyOrientedController</p>
 * <p>Description: </p>
 * @author xiaobo
 * @date 2017年3月27日
 */
@RestController
@RequestMapping(value = "/policyoriented")
public class PolicyOrientedController extends BaseController {
	
	@Autowired
	private PolicyOrientedService policyOrientedService;

	private static final Logger logger = LoggerFactory.getLogger(PolicyOrientedController.class);
	
	private void setDefaultCond(ConditionDTO cond) {
		if(cond.getPageSize()==null||cond.getPageSize()<=0){
			cond.setPageSize(SysConst.PAGE_SIZE);
		}
		if(cond.getPageNumber()==null||cond.getPageNumber()<=0){
			cond.setPageNumber(1);
		}
	}
 
	/**
	 * 政策导向---政策文本信息
	 * <p>Description: </p>
	 * @param cond
	 * @return 
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyTextInfo.json")
	public AjaxResult searchPolicyTextInfo(ConditionDTO cond) {
		try {
			List<String> list=new ArrayList<String>();
			list.add(SysConst.PUBLISHTYPE_CENTER);
			cond.setPublishType(list);
			cond.setPolicyInfoType("1");
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyOrientedService.searchPolicyTextInfo(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---政策文本信息异常：{} ",  e);
		}
		return fail("政策导向---政策文本信息失败，请重试");
	}
	
	/**
	 * 政策导向---政策图解信息
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyImageInfo.json")
	public AjaxResult searchPolicyImageInfo(ConditionDTO cond) {
		try {
			cond.setPolicyInfoType("2");
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyOrientedService.searchPolicyImageInfo(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---政策图解信息异常：{} ",  e);
		}
		return fail("政策导向---政策图解信息失败，请重试");
	}
	
	/**
	 * 政策导向---政策视频信息
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping("/searchPolicyVideoInfo.json")
	public AjaxResult searchPolicyVideoInfo(ConditionDTO cond) {
		try {
			cond.setPolicyInfoType("3");
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyOrientedService.searchPolicyVideoInfo(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---政策视频信息异常：{} ",  e);
		}
		return fail("政策导向---政策视频信息失败，请重试");
	}
	
	/**
	 * 政策导向---政策影响信息
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping("/searchPolicyAffectInfo.json")
	public AjaxResult searchPolicyAffectInfo(ConditionDTO cond) {
		try {
			cond.setYear(2017);
			JSONObject map = policyOrientedService.searchPolicyAffectInfo(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---政策影响信息异常：{} ",  e);
		}
		return fail("政策导向---政策影响信息失败，请重试");
	}
	/**
	 * 政策导向---政策影响信息--行业分布
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping("/searchPolicyAffectIndustryTrent.json")
	public AjaxResult searchPolicyAffectIndustryTrent(ConditionDTO cond) {
		try {
			cond.setDataType(SysConst.DATATYPE_RECRUITMENT);
			JSONObject map = policyOrientedService.searchPolicyAffectIndustryTrent(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---政策影响信息--行业分布异常：{} ",  e);
		}
		return fail("政策导向---政策影响信息--行业分布失败，请重试");
	}
	
	
	/**
	 * 政策导向---具体报道内容分析--媒体转发量分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping("/searchPolicyMediaTranspondAmount.json")
	public AjaxResult searchPolicyMediaTranspondAmount(ConditionDTO cond) {
		try {
			cond.setDataType(SysConst.DATATYPE_POLICY);
			//1,媒体;2,社交
			cond.setReportType(1L);
			JSONObject map = policyOrientedService.searchPolicyMediaTranspondAmount(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---具体报道内容分析--媒体转发量分析异常：{} ",  e);
		}
		return fail("政策导向---具体报道内容分析--媒体转发量分析失败，请重试");
	}
	
	/**
	 * 政策导向---具体报道内容分析--社交转发量分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping("/searchPolicySocialTranspondAmount.json")
	public AjaxResult searchPolicySocialTranspondAmount(ConditionDTO cond) {
		try {
			cond.setDataType(SysConst.DATATYPE_POLICY);
			setDefaultCond(cond);
			//1,媒体;2,社交
			cond.setReportType(2L);
			 
			JSONObject map = policyOrientedService.searchPolicySocialTranspondAmount(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---具体报道内容分析--社交转发量分析异常：{} ",  e);
		}
		return fail("政策导向---具体报道内容分析--社交转发量分析失败，请重试");
	}
	
	/**
	 * 政策导向---具体报道内容分析--用户评论量分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping("/searchPolicyUserCommentAmount.json")
	public AjaxResult searchPolicyUserCommentAmount(ConditionDTO cond) {
		try {
			cond.setDataType(SysConst.DATATYPE_POLICY);
			setDefaultCond(cond);
			 
			JSONObject map = policyOrientedService.searchPolicyUserCommentAmount(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---具体报道内容分析--用户评论量分析异常：{} ",  e);
		}
		return fail("政策导向---具体报道内容分析--用户评论量分析失败，请重试");
	}
	
	/**
	 * 政策导向---具体报道内容分析--媒体倾向分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping("/searchPolicyEmotionAnalysis.json")
	public AjaxResult searchPolicyEmotionAnalysis(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyOrientedService.searchPolicyEmotionAnalysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---具体报道内容分析--媒体倾向分析异常：{} ",  e);
		}
		return fail("政策导向---具体报道内容分析--媒体倾向分析失败，请重试");
	}
	
	/**
	 * 政策导向---文章趋势分析--各媒体用户评论总量排行
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping("/searchPolicyMediaCommentTotalRanking.json")
	public AjaxResult searchPolicyMediaCommentTotalRanking(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			//1,媒体;2,社交
			cond.setReportType(1L);
			//开始结束时间
			cond.setDate(new Date());
			cond.setStartTime(DateUtils.getFormatTime(DateUtils.getWeekAgoNow(cond.getDate())));
			cond.setEndTime(DateUtils.getFormatTime(cond.getDate()));
			JSONObject map = policyOrientedService.searchPolicyMediaCommentTotalRanking(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---文章趋势分析--各媒体用户评论总量排行异常：{} ",  e);
		}
		return fail("政策导向---文章趋势分析--各媒体用户评论总量排行失败，请重试");
	}
	
	/**
	 * 政策导向---文章趋势分析--各媒体文章占比
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping("/searchPolicyMediaArticleProportion.json")
	public AjaxResult searchPolicyMediaArticleProportion(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			//1,媒体;2,社交
			cond.setReportType(1L);
			//开始结束时间
			cond.setDate(new Date());
			cond.setStartTime(DateUtils.getFormatTime(DateUtils.getWeekAgoNow(cond.getDate())));
			cond.setEndTime(DateUtils.getFormatTime(cond.getDate()));
			JSONObject map = policyOrientedService.searchPolicyMediaArticleProportion(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---文章趋势分析--各媒体文章占比异常：{} ",  e);
		}
		return fail("政策导向---文章趋势分析--各媒体文章占比失败，请重试");
	}
	
	/**
	 * 政策导向---文章趋势分析--各媒体用户评论量趋势
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping("/searchPolicyMediaArticleTrend.json")
	public AjaxResult searchPolicyMediaArticleTrend(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			//1,媒体;2,社交
			cond.setReportType(1L);
			//开始结束时间
			cond.setDate(new Date());
			cond.setStartTime(DateUtils.getFormatTime(DateUtils.getWeekAgoNow(cond.getDate())));
			cond.setEndTime(DateUtils.getFormatTime(cond.getDate()));
			JSONObject map = policyOrientedService.searchPolicyMediaArticleTrend(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---文章趋势分析--各媒体用户评论量趋势异常：{} ",  e);
		}
		return fail("政策导向---文章趋势分析--各媒体用户评论量趋势失败，请重试");
	}
	
	
	/**
	 * 政策导向---倾向性、参与性分析--地图分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping("/searchPolicyMediaParMapAnaylysis.json")
	public AjaxResult searchPolicyMediaParMapAnaylysis(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			JSONObject map = policyOrientedService.searchPolicyMediaParMapAnaylysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---倾向性、参与性分析--地图分析异常：{} ",  e);
		}
		return fail("政策导向---倾向性、参与性分析--地图分析失败，请重试");
	}
	
	/**
	 * 政策导向---倾向性、参与性分析--媒体倾向分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping("/searchPolicyMediaParAnaylysis.json")
	public AjaxResult searchPolicyMediaParAnaylysis(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			//1,媒体;2,社交
			cond.setReportType(1L);
			JSONObject map = policyOrientedService.searchPolicyMediaParAnaylysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---倾向性、参与性分析--媒体倾向分析异常：{} ",  e);
		}
		return fail("政策导向---倾向性、参与性分析--媒体倾向分析失败，请重试");
	}
	
	/**
	 * 政策导向---倾向性、参与性分析--社交倾向分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping("/searchPolicySocialParAnaylysis.json")
	public AjaxResult searchPolicySocialParAnaylysis(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			//1,媒体;2,社交
			cond.setReportType(2L);
			JSONObject map = policyOrientedService.searchPolicySocialParAnaylysis(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---倾向性、参与性分析--社交倾向分析异常：{} ",  e);
		}
		return fail("政策导向---倾向性、参与性分析--社交倾向分析失败，请重试");
	}
	
	/**
	 * 政策导向---专题事件时间轴
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月29日
	 */
	@RequestMapping("/searchPolicySpecialEventShaft.json")
	public AjaxResult searchPolicySpecialEventShaft(ConditionDTO cond) {
		try {
			cond.setPageNumber(1);
			cond.setPageSize(100);
			JSONObject map = policyOrientedService.searchPolicySpecialEventShaft(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---专题事件时间轴异常：{} ",  e);
		}
		return fail("政策导向---专题事件时间轴失败，请重试");
	}
	
	/**
	 * 政策导向---热点关键词
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月29日
	 */
	@RequestMapping("/searchPolicyHotKeyWords.json")
	public AjaxResult searchPolicyHotKeyWords(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyOrientedService.searchPolicyHotKeyWords(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---热点关键词异常：{} ",  e);
		}
		return fail("政策导向---热点关键词失败，请重试");
	}
	
	/**
	 * 政策导向---热点关键词--才出现频率
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月29日
	 */
	@RequestMapping("/searchPolicyHotKeyWordsFrequency.json")
	public AjaxResult searchPolicyHotKeyWordsFrequency(ConditionDTO cond) {
		try {
			JSONObject map = policyOrientedService.searchPolicyHotKeyWordsFrequency(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---热点关键词异常：{} ",  e);
		}
		return fail("政策导向---热点关键词失败，请重试");
	}
	
	/**
	 * 政策导向---热点事件地点分布
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月29日
	 */
	@RequestMapping("/searchPolicyHotEventPlaceDistrbute.json")
	public AjaxResult searchPolicyHotEventDistrbute(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			cond.setHotEventMark(1L);
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyOrientedService.searchPolicyHotEventPlaceDistrbute(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---热点事件地点分布异常：{} ",  e);
		}
		return fail("政策导向---热点事件地点分布失败，请重试");
	}
	
	/**
	 * 政策导向---热点事件数量分布
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月29日
	 */
	@RequestMapping("/searchPolicyHotEventAmountDistrbute.json")
	public AjaxResult searchPolicyHotEventAmountDistrbute(ConditionDTO cond) {
		try {
			setDefaultCond(cond);
			cond.setHotEventMark(1L);
			cond.setDataType(SysConst.DATATYPE_POLICY);
			JSONObject map = policyOrientedService.searchPolicyHotEventAmountDistrbute(cond);
			return success(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("政策导向---热点事件地点分布异常：{} ",  e);
		}
		return fail("政策导向---热点事件地点分布失败，请重试");
	}
}