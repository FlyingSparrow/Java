package com.huishu.ieanalysis.controller;

import com.alibaba.fastjson.JSONObject;
import com.huishu.base.bean.AjaxResult;
import com.huishu.base.controller.BaseController;
import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.service.EnergyPolicyService;
import com.huishu.ieanalysis.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: EnergyPolicyController</p>
 * <p>Description: </p>
 *
 * @author xiaobo
 * @date 2017年3月21日
 */
@RestController
@RequestMapping(value = "/energyPolicy")
public class EnergyPolicyController extends BaseController {

    @Autowired
    private EnergyPolicyService energyPolicyService;

    private void setDefaultCond(ConditionDTO cond) {
        cond.setYear(DateUtils.getCurrentYear());
    }

    /**
     * 投资分析--区域投资总额及投资增长率
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchInvestmentAmountAndGrowthRate.json", method = RequestMethod.POST)
    public AjaxResult searchInvestmentAmountAndGrowthRate(ConditionDTO cond) {
        setDefaultCond(cond);
        cond.setDataType(SysConst.DataType.INVESTMENT.getCode());
        JSONObject map = energyPolicyService.searchInvestmentAmountAndGrowthRate(cond);
        return success(map);
    }

    /**
     * 投资分析--热门投资投资方向TOP5（全年）
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchHotInvestmentIndustryTop.json", method = RequestMethod.POST)
    public AjaxResult searchHotInvestmentIndustryTop(ConditionDTO cond) {
        cond.setDataType(SysConst.DataType.INVESTMENT.getCode());
        JSONObject map = energyPolicyService.searchHotInvestmentIndustryTop(cond);
        return success(map);
    }

    /**
     * 投资分析--区域投资方向分布
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchInvestmentIndustryDistribute.json", method = RequestMethod.POST)
    public AjaxResult searchInvestmentIndustryDistribute(ConditionDTO cond) {
        cond.setDataType(SysConst.DataType.INVESTMENT.getCode());
        JSONObject map = energyPolicyService.searchInvestmentIndustryDistribute(cond);
        return success(map);
    }

    /**
     * 注册量分析--区域新企业注册量比对
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchRegistrationsContrast.json", method = RequestMethod.POST)
    public AjaxResult searchRegistrationsContrast(ConditionDTO cond) {
        JSONObject map = energyPolicyService.searchRegistrationsContrast(cond);
        return success(map);
    }

    /**
     * 注册量分析--区域新企业注册增长比对
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchRegistrationsRateContrast.json", method = RequestMethod.POST)
    public AjaxResult searchRegistrationsRateContrast(ConditionDTO cond) {
        JSONObject map = energyPolicyService.searchRegistrationsRateContrast(cond);
        return success(map);
    }


    /**
     * 注册效率分析--新企业注册时间分布
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchNewEnterpriseRegistrationTimeDistribution.json", method = RequestMethod.POST)
    public AjaxResult searchNewEnterpriseRegistrationTimeDistribution(ConditionDTO cond) {
        JSONObject map = energyPolicyService.searchNewEnterpriseRegistrationTimeDistribution(cond);
        return success(map);
    }


    /**
     * 注册效率分析--新企业注册时间与满意度统计
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchNewEnterpriseRegistrationTimeAndSatisfactionStatistics.json", method = RequestMethod.POST)
    public AjaxResult searchNewEnterpriseRegistrationTimeAndSatisfactionStatistics(ConditionDTO cond) {
        JSONObject map = energyPolicyService.searchNewEnterpriseRegistrationTimeAndSatisfactionStatistics(cond);
        return success(map);
    }

    /**
     * 注册效率分析--新企业注册时间同期比对
     * <p>Description: </p>
     *
     * @param cond
     * @return
     * @author xiaobo
     * @date 2017年3月21日
     */
    @RequestMapping(value = "/searchNewEnterpriseRegistrationThanSamePeriod.json", method = RequestMethod.POST)
    public AjaxResult searchNewEnterpriseRegistrationThanSamePeriod(ConditionDTO cond) {
        setDefaultCond(cond);
        cond.setDataType(SysConst.DataType.INDUSTRY.getCode());
        JSONObject map = energyPolicyService.searchNewEnterpriseRegistrationThanSamePeriod(cond);
        return success(map);
    }

}