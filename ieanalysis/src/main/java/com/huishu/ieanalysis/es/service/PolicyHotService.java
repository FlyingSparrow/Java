package com.huishu.ieanalysis.es.service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.dto.ConditionDTO;

/**
 * @author wangjianchun
 */
public interface PolicyHotService {

    JSONObject searchPolicyHotAreaFocusMapAnalysis(ConditionDTO cond);

    JSONObject searchPolicyHotAreaFocusRankAnalysis(ConditionDTO cond);

    JSONObject searchPolicyHotAreaFocusSocialSubjectAnalysis(
            ConditionDTO cond);

    JSONObject searchPolicyHotChannelSiteAnalysis(ConditionDTO cond);

    JSONObject searchPolicyHotChannelSocialSubjectAnalysis(
            ConditionDTO cond);

    JSONObject searchPolicyHotMediaChannelEmotionAnalysis(
            ConditionDTO cond);

    JSONObject searchPolicyHotMediaChannelRankAnalysis(
            ConditionDTO cond);

    JSONObject searchPolicyHotMediaChannelDistributeAnalysis(
            ConditionDTO cond);

    JSONObject searchPolicyHotSocialEmotionAnalysis(ConditionDTO cond);

    JSONObject searchPolicyHotSocialChannelAnalysis(ConditionDTO cond);

    JSONObject searchPolicyHotSocialChannelDistributeAnalysis(
            ConditionDTO cond);

    JSONObject searchPolicyHotIndustryEmotionAnalysis(ConditionDTO cond);

    JSONObject searchPolicyHotIndustryRankAnalysis(ConditionDTO cond);

    JSONObject searchPolicyHotIndustryDistributeAnalysis(
            ConditionDTO cond);
}