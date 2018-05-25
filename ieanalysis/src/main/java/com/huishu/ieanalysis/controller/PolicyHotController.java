package com.huishu.ieanalysis.controller;

import com.alibaba.fastjson.JSONObject;
import com.huishu.base.bean.AjaxResult;
import com.huishu.base.controller.BaseController;
import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.service.PolicyHotService;
import com.huishu.ieanalysis.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: PolicyHotController</p>
 * <p>Description: </p>
 *
 * @author xiaobo
 * @date 2017年3月29日
 */
@RestController
@RequestMapping(value = "/policyhot")
public class PolicyHotController extends BaseController {

    @Autowired
    private PolicyHotService policyHotService;

    private void setDefaultCond(ConditionDTO cond) {
        cond.setYear(DateUtils.getCurrentYear());
    }

    /**
     * 政策热点---区域热点分析--区域双创政策关注度热力图
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyHotAreaFocusMapAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyHotAreaFocusMapAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        cond.setDataType(SysConst.DATATYPE_POLICY);
        JSONObject map = policyHotService.searchPolicyHotAreaFocusMapAnalysis(cond);
        return success(map);
    }

    /**
     * 政策热点---区域热点分析--双创政策关注度地区排行
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyHotAreaFocusRankAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyHotAreaFocusRankAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        cond.setDataType(SysConst.DATATYPE_POLICY);
        JSONObject map = policyHotService.searchPolicyHotAreaFocusRankAnalysis(cond);
        return success(map);
    }

    /**
     * 政策热点---区域热点分析--双创政策行业关注度
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyHotAreaFocusSocialSubjectAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyHotAreaFocusSocialSubjectAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        cond.setDataType(SysConst.DATATYPE_POLICY);
        JSONObject map = policyHotService.searchPolicyHotAreaFocusSocialSubjectAnalysis(cond);
        return success(map);
    }


    /**
     * 政策热点---关注渠道分析--双创政策关注度媒体渠道分布
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyHotChannelSiteAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyHotChannelSiteAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        cond.setVectorType("1");
        cond.setReportType(1L);
        cond.setDataType(SysConst.DATATYPE_POLICY);
        JSONObject map = policyHotService.searchPolicyHotChannelSiteAnalysis(cond);
        return success(map);
    }

    /**
     * 政策热点---关注渠道分析--不同行业关注政策媒体渠道分布
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyHotChannelSocialSubjectAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyHotChannelSocialSubjectAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        cond.setVectorType("1");
        cond.setReportType(1L);
        cond.setDataType(SysConst.DATATYPE_POLICY);
        JSONObject map = policyHotService.searchPolicyHotChannelSocialSubjectAnalysis(cond);
        return success(map);
    }


    /**
     * 政策热点---媒体参与度分析--双创政策媒体渠道倾向性分布
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyHotMediaChannelEmotionAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyHotMediaChannelEmotionAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        cond.setVectorType("1");
        cond.setDataType(SysConst.DATATYPE_POLICY);
        JSONObject map = policyHotService.searchPolicyHotMediaChannelEmotionAnalysis(cond);
        return success(map);
    }

    /**
     * 政策热点---媒体参与度分析--各大媒体渠道政策参与度排行
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyHotMediaChannelRankAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyHotMediaChannelRankAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        //1,媒体;2, 社会 ;3,行业;
        cond.setVectorType("1");
        cond.setDataType(SysConst.DATATYPE_POLICY);
        JSONObject map = policyHotService.searchPolicyHotMediaChannelRankAnalysis(cond);
        return success(map);
    }

    /**
     * 政策热点---媒体参与度分析--各大媒体渠道倾向性分布统计
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyHotMediaChannelDistributeAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyHotMediaChannelDistributeAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        //1,媒体;2, 社会 ;3,行业;
        cond.setVectorType("1");
        cond.setDataType(SysConst.DATATYPE_POLICY);
        JSONObject map = policyHotService.searchPolicyHotMediaChannelDistributeAnalysis(cond);
        return success(map);
    }


    /**
     * 政策热点---社会参与度分析--双创政策社会渠道倾向性分布
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyHotSocialEmotionAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyHotSocialEmotionAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        //1,媒体;2, 社会 ;3,行业;
        cond.setVectorType("2");
        cond.setDataType(SysConst.DATATYPE_POLICY);
        JSONObject map = policyHotService.searchPolicyHotSocialEmotionAnalysis(cond);
        return success(map);
    }

    /**
     * 政策热点---社会参与度分析--社会渠道政策参与度排行
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyHotSocialChannelAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyHotSocialChannelAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        //1,媒体;2, 社会 ;3,行业;
        cond.setVectorType("2");
        cond.setDataType(SysConst.DATATYPE_POLICY);
        JSONObject map = policyHotService.searchPolicyHotSocialChannelAnalysis(cond);
        return success(map);
    }

    /**
     * 政策热点---社会参与度分析--各大社会渠道倾向性分布统计
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyHotSocialChannelDistributeAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyHotSocialChannelDistributeAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        //1,媒体;2, 社会 ;3,行业;
        cond.setVectorType("2");
        cond.setDataType(SysConst.DATATYPE_POLICY);
        JSONObject map = policyHotService.searchPolicyHotSocialChannelDistributeAnalysis(cond);
        return success(map);
    }


    /**
     * 政策热点---行业参与度分析--双创政策行业渠道倾向性分布
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyHotIndustryEmotionAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyHotIndustryEmotionAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        //1,媒体;2, 社会 ;3,行业;
        cond.setVectorType("3");
        cond.setDataType(SysConst.DATATYPE_POLICY);
        JSONObject map = policyHotService.searchPolicyHotIndustryEmotionAnalysis(cond);
        return success(map);
    }


    /**
     * 政策热点---行业参与度分析--政策参与度行业排行
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyHotIndustryRankAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyHotIndustryRankAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        //1,媒体;2, 社会 ;3,行业;
        cond.setVectorType("3");
        cond.setDataType(SysConst.DATATYPE_POLICY);
        JSONObject map = policyHotService.searchPolicyHotIndustryRankAnalysis(cond);
        return success(map);
    }


    /**
     * 政策热点---行业参与度分析--各大行业渠道倾向性分布统计
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/searchPolicyHotIndustryDistributeAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyHotIndustryDistributeAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        //1,媒体;2, 社会 ;3,行业;
        cond.setVectorType("3");
        cond.setDataType(SysConst.DATATYPE_POLICY);
        JSONObject map = policyHotService.searchPolicyHotIndustryDistributeAnalysis(cond);
        return success(map);
    }
}