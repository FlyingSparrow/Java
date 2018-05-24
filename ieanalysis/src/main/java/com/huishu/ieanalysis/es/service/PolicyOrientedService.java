package com.huishu.ieanalysis.es.service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.dto.ConditionDTO;

/**
 * @author wangjianchun
 */
public interface PolicyOrientedService {

    JSONObject searchPolicyTextInfo(ConditionDTO cond);

    JSONObject searchPolicyImageInfo(ConditionDTO cond);

    JSONObject searchPolicyVideoInfo(ConditionDTO cond);

    JSONObject searchPolicyAffectInfo(ConditionDTO cond);

    JSONObject searchPolicyAffectIndustryTrent(ConditionDTO cond);

    JSONObject searchPolicyMediaTranspondAmount(ConditionDTO cond);

    JSONObject searchPolicySocialTranspondAmount(ConditionDTO cond);

    JSONObject searchPolicyUserCommentAmount(ConditionDTO cond);

    JSONObject searchPolicyEmotionAnalysis(ConditionDTO cond);

    JSONObject searchPolicyMediaCommentTotalRanking(ConditionDTO cond);

    JSONObject searchPolicyMediaArticleProportion(ConditionDTO cond);

    JSONObject searchPolicyMediaArticleTrend(ConditionDTO cond);

    JSONObject searchPolicyMediaParMapAnaylysis(ConditionDTO cond);

    JSONObject searchPolicyMediaParAnaylysis(ConditionDTO cond);

    JSONObject searchPolicySocialParAnaylysis(ConditionDTO cond);

    JSONObject searchPolicySpecialEventShaft(ConditionDTO cond);

    JSONObject searchPolicyHotKeyWords(ConditionDTO cond);

    JSONObject searchPolicyHotEventPlaceDistrbute(ConditionDTO cond);

    JSONObject searchPolicyHotEventAmountDistrbute(ConditionDTO cond);

    JSONObject searchPolicyHotKeyWordsFrequency(ConditionDTO cond);

}