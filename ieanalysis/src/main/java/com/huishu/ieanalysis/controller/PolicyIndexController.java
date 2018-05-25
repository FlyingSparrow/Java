package com.huishu.ieanalysis.controller;

import com.alibaba.fastjson.JSONObject;
import com.huishu.base.bean.AjaxResult;
import com.huishu.base.controller.BaseController;
import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.service.PolicyIndexService;
import com.huishu.ieanalysis.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: PolicyIndexController</p>
 * <p>Description: </p>
 *
 * @author xiaobo
 * @date 2017年3月27日
 */
@RestController
@RequestMapping(value = "/policyindex")
public class PolicyIndexController extends BaseController {

    @Autowired
    private PolicyIndexService policyIndexService;

    private void setDefaultCond(ConditionDTO cond) {
        cond.setYear(DateUtils.getCurrentYear());
    }

    /**
     * 资本指数分析--融资金额分析
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchFinancingAmountAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchFinancingAmountAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        cond.setDataType(SysConst.DATATYPE_INVESTMENT);
        List<String> publish = new ArrayList<String>();
        publish.add("9");
        cond.setPublishType(publish);
        JSONObject map = policyIndexService.searchFinancingAmountAnalysis(cond);
        return success(map);
    }

    /**
     * 资本指数分析--并购金额分析
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchMergersAmountAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchMergersAmountAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        cond.setDataType(SysConst.DATATYPE_INVESTMENT);
        List<String> publish = new ArrayList<String>();
        publish.add("13");
        cond.setPublishType(publish);
        JSONObject map = policyIndexService.searchMergersAmountAnalysis(cond);
        return success(map);
    }

    /**
     * 资本指数分析--退出金额分析
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchQuitAmountAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchQuitAmountAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        cond.setDataType(SysConst.DATATYPE_INVESTMENT);
        List<String> publish = new ArrayList<String>();
        publish.add("14");
        cond.setPublishType(publish);
        JSONObject map = policyIndexService.searchQuitAmountAnalysis(cond);
        return success(map);
    }


    /**
     * 人才指数分析--网络招聘岗位数分析
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchJobsNumberAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchJobsNumberAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        cond.setDataType(SysConst.DATATYPE_RECRUITMENT);
        JSONObject map = policyIndexService.searchJobsNumberAnalysis(cond);
        return success(map);
    }

    /**
     * 人才指数分析--网络招聘平均薪酬分析
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchAvgRemunerationAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchAvgRemunerationAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        cond.setDataType(SysConst.DATATYPE_RECRUITMENT);
        JSONObject map = policyIndexService.searchAvgRemunerationAnalysis(cond);
        return success(map);
    }

    /**
     * 人才指数分析--求职热度分析
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchCoverHeatAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchCoverHeatAnalysis(ConditionDTO cond) {
        JSONObject map = policyIndexService.searchCoverHeatAnalysis(cond);
        return success(map);
    }

    /**
     * 人才指数分析--双高人才比例分析
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchCombinationTalentRatioAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchCombinationTalentRatioAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        cond.setDataType(SysConst.DATATYPE_RECRUITMENT);
        JSONObject map = policyIndexService.searchCombinationTalentRatioAnalysis(cond);
        return success(map);
    }


    /**
     * 健康度指数分析--企业核心竞争力分析
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchEnterpriseCoreCompetitivenessAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchEnterpriseCoreCompetitivenessAnalysis(ConditionDTO cond) {
        JSONObject map = policyIndexService.searchEnterpriseCoreCompetitivenessAnalysis(cond);
        return success(map);
    }

    /**
     * 健康度指数分析--企业市场力分析
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchEnterpriseMarketForceAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchEnterpriseMarketForceAnalysis(ConditionDTO cond) {
        JSONObject map = policyIndexService.searchEnterpriseMarketForceAnalysis(cond);
        return success(map);
    }

    /**
     * 健康度指数分析--企业创新创造力
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchEnterpriseInnovationAndCreativityAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchEnterpriseInnovationAndCreativityAnalysis(ConditionDTO cond) {
        JSONObject map = policyIndexService.searchEnterpriseInnovationAndCreativityAnalysis(cond);
        return success(map);
    }

    /**
     * 经营环境指数分析--经营环境指数分析
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchManagementEnvironmentAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchManagementEnvironmentAnalysis(ConditionDTO cond) {
        setDefaultCond(cond);
        List<String> list = new ArrayList<String>();
        list.add(SysConst.PUBLISHTYPE_CENTER);
        list.add(SysConst.PUBLISHTYPE_LOCAL);
        cond.setPublishType(list);
        cond.setDataType(SysConst.DATATYPE_POLICY);
        JSONObject map = policyIndexService.searchManagementEnvironmentAnalysis(cond);
        return success(map);
    }

    /**
     * 经营环境指数分析--中央政策发布数量
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchCentralPolicyPublishAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchCentralPolicyPublishAnalysis(ConditionDTO cond) {
        cond.setDataType(SysConst.DATATYPE_POLICY);
        setDefaultCond(cond);
        List<String> list = new ArrayList<String>();
        list.add(SysConst.PUBLISHTYPE_CENTER);
        cond.setPublishType(list);
        JSONObject map = policyIndexService.searchCentralPolicyPublishAnalysis(cond);
        return success(map);
    }

    /**
     * 经营环境指数分析--地方政策发布数量
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchLocalPolicyPublishAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchLocalPolicyPublishAnalysis(ConditionDTO cond) {
        cond.setDataType(SysConst.DATATYPE_POLICY);
        setDefaultCond(cond);
        List<String> list = new ArrayList<String>();
        list.add(SysConst.PUBLISHTYPE_LOCAL);
        cond.setPublishType(list);
        JSONObject map = policyIndexService.searchLocalPolicyPublishAnalysis(cond);
        return success(map);
    }

    /**
     * 经营环境指数分析--知识产权保护诉讼数量
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchProtectionIntellectualLitigationAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchProtectionIntellectualLitigationAnalysis(ConditionDTO cond) {
        JSONObject map = policyIndexService.searchProtectionIntellectualLitigationAnalysis(cond);
        return success(map);
    }

    /**
     * 经营环境指数分析--报道数/关注度
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchPolicyReportAndFocusAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchPolicyReportAndFocusAnalysis(ConditionDTO cond) {
        List<String> list = new ArrayList<String>();
        list.add(SysConst.PUBLISHTYPE_CENTER);
        list.add(SysConst.PUBLISHTYPE_LOCAL);
        cond.setPublishType(list);
        cond.setDataType(SysConst.DATATYPE_POLICY);
        setDefaultCond(cond);
        JSONObject map = policyIndexService.searchPolicyReportAndFocusAnalysis(cond);
        return success(map);
    }

    /**
     * 活跃度指数分析--注册企业数量--环比增长
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchRegisterAndSurviveNumAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchRegisterAndSurviveNumAnalysis(ConditionDTO cond) {
        JSONObject map = policyIndexService.searchRegisterAndSurviveNumAnalysis(cond);
        return success(map);
    }

    /**
     * 活跃度指数分析--注册企业资金总额
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchRegisterAndSurviveAmountAnalysis.json", method = RequestMethod.POST)
    public AjaxResult searchRegisterAndSurviveAmountAnalysis(ConditionDTO cond) {
        JSONObject map = policyIndexService.searchRegisterAndSurviveAmountAnalysis(cond);
        return success(map);
    }

}