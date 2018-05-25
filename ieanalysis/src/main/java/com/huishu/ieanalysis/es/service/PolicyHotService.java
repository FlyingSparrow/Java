package com.huishu.ieanalysis.es.service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.dto.ConditionDTO;

/**
 * @author wangjianchun
 */
public interface PolicyHotService {

    /**
     * 政策热点---区域热点分析--区域双创政策关注度热力图
     * @param cond
     * @return
     */
    JSONObject searchPolicyHotAreaFocusMapAnalysis(ConditionDTO cond);

    /**
     * 政策热点---区域热点分析--双创政策关注度地区排行
     * @param cond
     * @return
     */
    JSONObject searchPolicyHotAreaFocusRankAnalysis(ConditionDTO cond);

    /**
     * 政策热点---区域热点分析--双创政策行业关注度
     * @param cond
     * @return
     */
    JSONObject searchPolicyHotAreaFocusSocialSubjectAnalysis(
            ConditionDTO cond);

    /**
     * 政策热点---关注渠道分析--双创政策关注度媒体渠道分布
     * @param cond
     * @return
     */
    JSONObject searchPolicyHotChannelSiteAnalysis(ConditionDTO cond);

    /**
     * 政策热点---关注渠道分析--不同行业关注政策媒体渠道分布
     * @param cond
     * @return
     */
    JSONObject searchPolicyHotChannelSocialSubjectAnalysis(
            ConditionDTO cond);

    /**
     * 政策热点---媒体参与度分析--双创政策媒体渠道倾向性分布
     * @param cond
     * @return
     */
    JSONObject searchPolicyHotMediaChannelEmotionAnalysis(
            ConditionDTO cond);

    /**
     * 政策热点---媒体参与度分析--各大媒体渠道政策参与度排行
     * @param cond
     * @return
     */
    JSONObject searchPolicyHotMediaChannelRankAnalysis(
            ConditionDTO cond);

    /**
     * 政策热点---媒体参与度分析--各大媒体渠道倾向性分布统计
     * @param cond
     * @return
     */
    JSONObject searchPolicyHotMediaChannelDistributeAnalysis(
            ConditionDTO cond);

    /**
     * 政策热点---社会参与度分析--双创政策社会渠道倾向性分布
     * @param cond
     * @return
     */
    JSONObject searchPolicyHotSocialEmotionAnalysis(ConditionDTO cond);

    /**
     * 政策热点---社会参与度分析--社会渠道政策参与度排行
     * @param cond
     * @return
     */
    JSONObject searchPolicyHotSocialChannelAnalysis(ConditionDTO cond);

    /**
     * 政策热点---社会参与度分析--各大社会渠道倾向性分布统计
     * @param cond
     * @return
     */
    JSONObject searchPolicyHotSocialChannelDistributeAnalysis(
            ConditionDTO cond);

    /**
     * 政策热点---行业参与度分析--双创政策行业渠道倾向性分布
     * @param cond
     * @return
     */
    JSONObject searchPolicyHotIndustryEmotionAnalysis(ConditionDTO cond);

    /**
     * 政策热点---行业参与度分析--政策参与度行业排行
     * @param cond
     * @return
     */
    JSONObject searchPolicyHotIndustryRankAnalysis(ConditionDTO cond);

    /**
     * 政策热点---行业参与度分析--各大行业渠道倾向性分布统计
     * @param cond
     * @return
     */
    JSONObject searchPolicyHotIndustryDistributeAnalysis(
            ConditionDTO cond);
}