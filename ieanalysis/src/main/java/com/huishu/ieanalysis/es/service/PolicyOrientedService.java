package com.huishu.ieanalysis.es.service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.dto.ConditionDTO;

/**
 * @author wangjianchun
 */
public interface PolicyOrientedService {

    /**
     * 政策导向---政策文本信息
     * @param cond
     * @return
     */
    JSONObject searchPolicyTextInfo(ConditionDTO cond);

    /**
     * 政策导向---政策图解信息
     * @param cond
     * @return
     */
    JSONObject searchPolicyImageInfo(ConditionDTO cond);

    /**
     * 政策导向---政策视频信息
     * @param cond
     * @return
     */
    JSONObject searchPolicyVideoInfo(ConditionDTO cond);

    /**
     * 政策导向---政策影响信息
     * @param cond
     * @return
     */
    JSONObject searchPolicyAffectInfo(ConditionDTO cond);

    /**
     * 政策导向---政策影响信息--行业分布
     * @param cond
     * @return
     */
    JSONObject searchPolicyAffectIndustryTrent(ConditionDTO cond);

    /**
     * 政策导向---具体报道内容分析--媒体转发量分析
     * @param cond
     * @return
     */
    JSONObject searchPolicyMediaTranspondAmount(ConditionDTO cond);

    /**
     * 政策导向---具体报道内容分析--社交转发量分析
     * @param cond
     * @return
     */
    JSONObject searchPolicySocialTranspondAmount(ConditionDTO cond);

    /**
     * 政策导向---具体报道内容分析--用户评论量分析
     * @param cond
     * @return
     */
    JSONObject searchPolicyUserCommentAmount(ConditionDTO cond);

    /**
     * 政策导向---具体报道内容分析--媒体倾向分析
     * @param cond
     * @return
     */
    JSONObject searchPolicyEmotionAnalysis(ConditionDTO cond);

    /**
     * 政策导向---文章趋势分析--各媒体用户评论总量排行
     * @param cond
     * @return
     */
    JSONObject searchPolicyMediaCommentTotalRanking(ConditionDTO cond);

    /**
     * 政策导向---文章趋势分析--各媒体文章占比
     * @param cond
     * @return
     */
    JSONObject searchPolicyMediaArticleProportion(ConditionDTO cond);

    /**
     * 政策导向---文章趋势分析--各媒体用户评论量趋势
     * @param cond
     * @return
     */
    JSONObject searchPolicyMediaArticleTrend(ConditionDTO cond);

    /**
     * 政策导向---倾向性、参与性分析--地图分析
     * @param cond
     * @return
     */
    JSONObject searchPolicyMediaParMapAnaylysis(ConditionDTO cond);

    /**
     * 政策导向---倾向性、参与性分析--媒体倾向分析
     * @param cond
     * @return
     */
    JSONObject searchPolicyMediaParAnaylysis(ConditionDTO cond);

    /**
     * 政策导向---倾向性、参与性分析--社交倾向分析
     * @param cond
     * @return
     */
    JSONObject searchPolicySocialParAnaylysis(ConditionDTO cond);

    /**
     * 政策导向---专题事件时间轴
     * @param cond
     * @return
     */
    JSONObject searchPolicySpecialEventShaft(ConditionDTO cond);

    /**
     * 政策导向---热点关键词
     * @param cond
     * @return
     */
    JSONObject searchPolicyHotKeyWords(ConditionDTO cond);

    /**
     * 政策导向---热点事件地点分布
     * @param cond
     * @return
     */
    JSONObject searchPolicyHotEventPlaceDistrbute(ConditionDTO cond);

    /**
     * 政策导向---热点事件数量分布
     * @param cond
     * @return
     */
    JSONObject searchPolicyHotEventAmountDistrbute(ConditionDTO cond);

    /**
     * 政策导向---热点关键词--出现频率
     * @param cond
     * @return
     */
    JSONObject searchPolicyHotKeyWordsFrequency(ConditionDTO cond);

}