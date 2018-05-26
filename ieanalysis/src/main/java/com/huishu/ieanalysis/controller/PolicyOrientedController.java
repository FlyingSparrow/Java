package com.huishu.ieanalysis.controller;

import com.alibaba.fastjson.JSONObject;
import com.huishu.base.bean.AjaxResult;
import com.huishu.base.controller.BaseController;
import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.service.PolicyOrientedService;
import com.huishu.ieanalysis.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(value = "/searchPolicyTextInfo.json", method = RequestMethod.POST)
	public AjaxResult searchPolicyTextInfo(ConditionDTO cond) {
		List<String> list=new ArrayList<String>();
		list.add(SysConst.PublishType.CENTER.getCode());
		cond.setPublishType(list);
		cond.setPolicyInfoType("1");
		cond.setDataType(SysConst.DataType.POLICY.getCode());
		JSONObject map = policyOrientedService.searchPolicyTextInfo(cond);
		return success(map);
	}
	
	/**
	 * 政策导向---政策图解信息
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping(value = "/searchPolicyImageInfo.json", method = RequestMethod.POST)
	public AjaxResult searchPolicyImageInfo(ConditionDTO cond) {
		cond.setPolicyInfoType("2");
		cond.setDataType(SysConst.DataType.POLICY.getCode());
		JSONObject map = policyOrientedService.searchPolicyImageInfo(cond);
		return success(map);
	}
	
	/**
	 * 政策导向---政策视频信息
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月27日
	 */
	@RequestMapping(value = "/searchPolicyVideoInfo.json", method = RequestMethod.POST)
	public AjaxResult searchPolicyVideoInfo(ConditionDTO cond) {
		cond.setPolicyInfoType("3");
		cond.setDataType(SysConst.DataType.POLICY.getCode());
		JSONObject map = policyOrientedService.searchPolicyVideoInfo(cond);
		return success(map);
	}
	
	/**
	 * 政策导向---政策影响信息
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping(value = "/searchPolicyAffectInfo.json", method = RequestMethod.POST)
	public AjaxResult searchPolicyAffectInfo(ConditionDTO cond) {
		cond.setYear(2017);
		JSONObject map = policyOrientedService.searchPolicyAffectInfo(cond);
		return success(map);
	}
	/**
	 * 政策导向---政策影响信息--行业分布
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping(value = "/searchPolicyAffectIndustryTrent.json", method = RequestMethod.POST)
	public AjaxResult searchPolicyAffectIndustryTrent(ConditionDTO cond) {
		cond.setDataType(SysConst.DataType.RECRUITMENT.getCode());
		JSONObject map = policyOrientedService.searchPolicyAffectIndustryTrent(cond);
		return success(map);
	}
	
	
	/**
	 * 政策导向---具体报道内容分析--媒体转发量分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping(value = "/searchPolicyMediaTranspondAmount.json", method = RequestMethod.POST)
	public AjaxResult searchPolicyMediaTranspondAmount(ConditionDTO cond) {
		cond.setDataType(SysConst.DataType.POLICY.getCode());
		//1,媒体;2,社交
		cond.setReportType(1L);
		JSONObject map = policyOrientedService.searchPolicyMediaTranspondAmount(cond);
		return success(map);
	}
	
	/**
	 * 政策导向---具体报道内容分析--社交转发量分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping(value = "/searchPolicySocialTranspondAmount.json", method = RequestMethod.POST)
	public AjaxResult searchPolicySocialTranspondAmount(ConditionDTO cond) {
		cond.setDataType(SysConst.DataType.POLICY.getCode());
		setDefaultCond(cond);
		//1,媒体;2,社交
		cond.setReportType(2L);

		JSONObject map = policyOrientedService.searchPolicySocialTranspondAmount(cond);
		return success(map);
	}
	
	/**
	 * 政策导向---具体报道内容分析--用户评论量分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping(value = "/searchPolicyUserCommentAmount.json", method = RequestMethod.POST)
	public AjaxResult searchPolicyUserCommentAmount(ConditionDTO cond) {
		cond.setDataType(SysConst.DataType.POLICY.getCode());
		setDefaultCond(cond);

		JSONObject map = policyOrientedService.searchPolicyUserCommentAmount(cond);
		return success(map);
	}
	
	/**
	 * 政策导向---具体报道内容分析--媒体倾向分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping(value = "/searchPolicyEmotionAnalysis.json", method = RequestMethod.POST)
	public AjaxResult searchPolicyEmotionAnalysis(ConditionDTO cond) {
		setDefaultCond(cond);
		cond.setDataType(SysConst.DataType.POLICY.getCode());
		JSONObject map = policyOrientedService.searchPolicyEmotionAnalysis(cond);
		return success(map);
	}
	
	/**
	 * 政策导向---文章趋势分析--各媒体用户评论总量排行
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping(value = "/searchPolicyMediaCommentTotalRanking.json", method = RequestMethod.POST)
	public AjaxResult searchPolicyMediaCommentTotalRanking(ConditionDTO cond) {
		setDefaultCond(cond);
		//1,媒体;2,社交
		cond.setReportType(1L);
		//开始结束时间
		cond.setDate(new Date());
		cond.setStartTime(DateUtils.getFormatTime(DateUtils.getWeekAgoNow(cond.getDate())));
		cond.setEndTime(DateUtils.getFormatTime(cond.getDate()));
		JSONObject map = policyOrientedService.searchPolicyMediaCommentTotalRanking(cond);
		return success(map);
	}
	
	/**
	 * 政策导向---文章趋势分析--各媒体文章占比
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping(value = "/searchPolicyMediaArticleProportion.json", method = RequestMethod.POST)
	public AjaxResult searchPolicyMediaArticleProportion(ConditionDTO cond) {
		setDefaultCond(cond);
		//1,媒体;2,社交
		cond.setReportType(1L);
		//开始结束时间
		cond.setDate(new Date());
		cond.setStartTime(DateUtils.getFormatTime(DateUtils.getWeekAgoNow(cond.getDate())));
		cond.setEndTime(DateUtils.getFormatTime(cond.getDate()));
		JSONObject map = policyOrientedService.searchPolicyMediaArticleProportion(cond);
		return success(map);
	}
	
	/**
	 * 政策导向---文章趋势分析--各媒体用户评论量趋势
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping(value = "/searchPolicyMediaArticleTrend.json", method = RequestMethod.POST)
	public AjaxResult searchPolicyMediaArticleTrend(ConditionDTO cond) {
		setDefaultCond(cond);
		//1,媒体;2,社交
		cond.setReportType(1L);
		//开始结束时间
		cond.setDate(new Date());
		cond.setStartTime(DateUtils.getFormatTime(DateUtils.getWeekAgoNow(cond.getDate())));
		cond.setEndTime(DateUtils.getFormatTime(cond.getDate()));
		JSONObject map = policyOrientedService.searchPolicyMediaArticleTrend(cond);
		return success(map);
	}
	
	
	/**
	 * 政策导向---倾向性、参与性分析--地图分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping(value = "/searchPolicyMediaParMapAnaylysis.json", method = RequestMethod.POST)
	public AjaxResult searchPolicyMediaParMapAnaylysis(ConditionDTO cond) {
		setDefaultCond(cond);
		JSONObject map = policyOrientedService.searchPolicyMediaParMapAnaylysis(cond);
		return success(map);
	}
	
	/**
	 * 政策导向---倾向性、参与性分析--媒体倾向分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping(value = "/searchPolicyMediaParAnaylysis.json", method = RequestMethod.POST)
	public AjaxResult searchPolicyMediaParAnaylysis(ConditionDTO cond) {
		setDefaultCond(cond);
		//1,媒体;2,社交
		cond.setReportType(1L);
		JSONObject map = policyOrientedService.searchPolicyMediaParAnaylysis(cond);
		return success(map);
	}
	
	/**
	 * 政策导向---倾向性、参与性分析--社交倾向分析
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月28日
	 */
	@RequestMapping(value = "/searchPolicySocialParAnaylysis.json", method = RequestMethod.POST)
	public AjaxResult searchPolicySocialParAnaylysis(ConditionDTO cond) {
		setDefaultCond(cond);
		//1,媒体;2,社交
		cond.setReportType(2L);
		JSONObject map = policyOrientedService.searchPolicySocialParAnaylysis(cond);
		return success(map);
	}
	
	/**
	 * 政策导向---专题事件时间轴
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月29日
	 */
	@RequestMapping(value = "/searchPolicySpecialEventShaft.json", method = RequestMethod.POST)
	public AjaxResult searchPolicySpecialEventShaft(ConditionDTO cond) {
		cond.setPageNumber(1);
		cond.setPageSize(100);
		JSONObject map = policyOrientedService.searchPolicySpecialEventShaft(cond);
		return success(map);
	}
	
	/**
	 * 政策导向---热点关键词
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月29日
	 */
	@RequestMapping(value = "/searchPolicyHotKeyWords.json", method = RequestMethod.POST)
	public AjaxResult searchPolicyHotKeyWords(ConditionDTO cond) {
		setDefaultCond(cond);
		cond.setDataType(SysConst.DataType.POLICY.getCode());
		JSONObject map = policyOrientedService.searchPolicyHotKeyWords(cond);
		return success(map);
	}
	
	/**
	 * 政策导向---热点关键词--出现频率
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月29日
	 */
	@RequestMapping(value = "/searchPolicyHotKeyWordsFrequency.json", method = RequestMethod.POST)
	public AjaxResult searchPolicyHotKeyWordsFrequency(ConditionDTO cond) {
		JSONObject map = policyOrientedService.searchPolicyHotKeyWordsFrequency(cond);
		return success(map);
	}
	
	/**
	 * 政策导向---热点事件地点分布
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月29日
	 */
	@RequestMapping(value = "/searchPolicyHotEventPlaceDistrbute.json", method = RequestMethod.POST)
	public AjaxResult searchPolicyHotEventDistrbute(ConditionDTO cond) {
		setDefaultCond(cond);
		cond.setHotEventMark(1L);
		cond.setDataType(SysConst.DataType.POLICY.getCode());
		JSONObject map = policyOrientedService.searchPolicyHotEventPlaceDistrbute(cond);
		return success(map);
	}
	
	/**
	 * 政策导向---热点事件数量分布
	 * <p>Description: </p>
	 * @param cond
	 * @return
	 * @author xiaobo
	 * @date 2017年3月29日
	 */
	@RequestMapping(value = "/searchPolicyHotEventAmountDistrbute.json", method = RequestMethod.POST)
	public AjaxResult searchPolicyHotEventAmountDistribute(ConditionDTO cond) {
		setDefaultCond(cond);
		cond.setHotEventMark(1L);
		cond.setDataType(SysConst.DataType.POLICY.getCode());
		JSONObject map = policyOrientedService.searchPolicyHotEventAmountDistrbute(cond);
		return success(map);
	}
}