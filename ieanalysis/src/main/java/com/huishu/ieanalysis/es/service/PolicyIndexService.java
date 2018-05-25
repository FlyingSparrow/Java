package com.huishu.ieanalysis.es.service;

import com.alibaba.fastjson.JSONObject;
import com.huishu.ieanalysis.dto.ConditionDTO;

/**
 * @author wangjianchun
 */
public interface PolicyIndexService {

    /**
     * 资本指数分析--融资金额分析
     * @param cond
     * @return
     */
    JSONObject searchFinancingAmountAnalysis(ConditionDTO cond);

    /**
     * 资本指数分析--并购金额分析
     * @param cond
     * @return
     */
    JSONObject searchMergersAmountAnalysis(ConditionDTO cond);

    /**
     * 资本指数分析--退出金额分析
     * @param cond
     * @return
     */
    JSONObject searchQuitAmountAnalysis(ConditionDTO cond);

    /**
     * 人才指数分析--网络招聘岗位数分析
     * @param cond
     * @return
     */
    JSONObject searchJobsNumberAnalysis(ConditionDTO cond);

    /**
     * 人才指数分析--网络招聘平均薪酬分析
     * @param cond
     * @return
     */
    JSONObject searchAvgRemunerationAnalysis(ConditionDTO cond);

    /**
     * 人才指数分析--求职热度分析
     * @param cond
     * @return
     */
    JSONObject searchCoverHeatAnalysis(ConditionDTO cond);

    /**
     * 人才指数分析--双高人才比例分析
     * @param cond
     * @return
     */
    JSONObject searchCombinationTalentRatioAnalysis(ConditionDTO cond);

    /**
     * 健康度指数分析--企业核心竞争力分析
     * @param cond
     * @return
     */
    JSONObject searchEnterpriseCoreCompetitivenessAnalysis(
            ConditionDTO cond);

    /**
     * 健康度指数分析--企业市场力分析
     * @param cond
     * @return
     */
    JSONObject searchEnterpriseMarketForceAnalysis(ConditionDTO cond);

    /**
     * 健康度指数分析--企业创新创造力
     * @param cond
     * @return
     */
    JSONObject searchEnterpriseInnovationAndCreativityAnalysis(
            ConditionDTO cond);

    /**
     * 经营环境指数分析--经营环境指数分析
     * @param cond
     * @return
     */
    JSONObject searchManagementEnvironmentAnalysis(ConditionDTO cond);

    /**
     * 经营环境指数分析--中央政策发布数量
     * @param cond
     * @return
     */
    JSONObject searchCentralPolicyPublishAnalysis(ConditionDTO cond);

    /**
     * 经营环境指数分析--地方政策发布数量
     * @param cond
     * @return
     */
    JSONObject searchLocalPolicyPublishAnalysis(ConditionDTO cond);

    /**
     * 经营环境指数分析--知识产权保护诉讼数量
     * @param cond
     * @return
     */
    JSONObject searchProtectionIntellectualLitigationAnalysis(
            ConditionDTO cond);

    /**
     * 经营环境指数分析--报道数/关注度
     * @param cond
     * @return
     */
    JSONObject searchPolicyReportAndFocusAnalysis(ConditionDTO cond);

    /**
     * 活跃度指数分析--注册企业数量--环比增长
     * @param cond
     * @return
     */
    JSONObject searchRegisterAndSurviveNumAnalysis(ConditionDTO cond);

    /**
     * 活跃度指数分析--注册企业资金总额
     * @param cond
     * @return
     */
    JSONObject searchRegisterAndSurviveAmountAnalysis(ConditionDTO cond);

}