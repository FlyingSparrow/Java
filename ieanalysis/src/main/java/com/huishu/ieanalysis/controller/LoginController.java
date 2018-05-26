package com.huishu.ieanalysis.controller;

import com.huishu.base.controller.BaseController;
import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.entity.DgapData;
import com.huishu.ieanalysis.es.service.PolicyHotTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>Title: LoginController</p>
 * <p>Description: </p>
 *
 * @author xiaobo
 * @date 2017年3月21日
 */
@Controller
@RequestMapping
public class LoginController extends BaseController {

    @Autowired
    private PolicyHotTopicService policyHotTopicService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(Map<String, Object> model) {
        return defaultRequest(model);
    }

    /**
     * 政策活力---投资分析
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月24日
     */
    @RequestMapping(value = "/energypolicy/investmentAnalysis.json", method = RequestMethod.GET)
    public String investmentAnalysis(Map<String, Object> model) {
        return defaultRequest(model);
    }

    private String defaultRequest(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();

        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 0);
        model.put("menuId", 0);
        model.put("floatName", "热点双创政策");
        return "energypolicy/investmentAnalysis";
    }

    /**
     * 政策活力---注册量分析
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月24日
     */
    @RequestMapping(value = "/energypolicy/registerAmountAnalysis.json", method = RequestMethod.GET)
    public String registerAmountAnalysis(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();
        ;
        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 0);
        model.put("menuId", 1);
        model.put("floatName", "热点双创政策");
        return "energypolicy/registerAmountAnalysis";
    }

    /**
     * 政策活力---注册效率
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月24日
     */
    @RequestMapping(value = "/energypolicy/registerRateAnalysis.json", method = RequestMethod.GET)
    public String registerRateAnalysis(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();

        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 0);
        model.put("menuId", 2);
        model.put("floatName", "热点双创政策");
        return "energypolicy/registerRateAnalysis";
    }

    /**
     * 政策指数---资本指数
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月24日
     */
    @RequestMapping(value = "/policyindex/capitalIndexAnalysis.json", method = RequestMethod.GET)
    public String capitalIndexAnalysis(Map<String, Object> model) {
        model.put("modelId", 1);
        model.put("menuId", 0);
        ConditionDTO cond = new ConditionDTO();
        cond.setDataType(SysConst.DataType.INVESTMENT.getCode());
        List<String> provinceList = policyHotTopicService.searchProvince(cond);
        model.put("provinceData", getSortProvince(provinceList));
        return "policyindex/capitalIndexAnalysis";
    }

    /**
     * 政策指数---人才指数
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月24日
     */
    @RequestMapping(value = "/policyindex/talentIndexAnalysis.json", method = RequestMethod.GET)
    public String talentIndexAnalysis(Map<String, Object> model) {
        model.put("modelId", 1);
        model.put("menuId", 1);
        ConditionDTO cond = new ConditionDTO();
        cond.setDataType(SysConst.DataType.RECRUITMENT.getCode());
        List<String> provinceList = policyHotTopicService.searchProvince(cond);
        model.put("provinceData", getSortProvince(provinceList));
        return "policyindex/talentIndexAnalysis";
    }

    /**
     * 政策指数---健康度指数
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月24日
     */
    @RequestMapping(value = "/policyindex/healthDegreeAnalysis.json", method = RequestMethod.GET)
    public String healthDegreeAnalysis(Map<String, Object> model) {
        model.put("modelId", 1);
        model.put("menuId", 2);
        return "policyindex/healthDegreeAnalysis";
    }

    /**
     * 政策指数---经营环境指数
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月24日
     */
    @RequestMapping(value = "/policyindex/managementEnvironmentAnalysis.json", method = RequestMethod.GET)
    public String managementEnvironmentAnalysis(Map<String, Object> model) {
        model.put("modelId", 1);
        model.put("menuId", 3);
        ConditionDTO cond = new ConditionDTO();
        cond.setDataType(SysConst.DataType.POLICY.getCode());
        List<String> list = new ArrayList<String>();
        list.add(SysConst.PublishType.CENTER.getCode());
        list.add(SysConst.PublishType.LOCAL.getCode());
        cond.setPublishType(list);
        List<String> provinceList = policyHotTopicService.searchProvince(cond);
        model.put("provinceData", getSortProvince(provinceList));
        return "policyindex/managementEnvironmentAnalysis";
    }

    /**
     * 政策指数---活跃度指数
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月24日
     */
    @RequestMapping(value = "/policyindex/livenessAnalysis.json", method = RequestMethod.GET)
    public String livenessAnalysis(Map<String, Object> model) {
        model.put("modelId", 1);
        model.put("menuId", 4);
        return "policyindex/livenessAnalysis";
    }

    /**
     * 政策导向---文本信息
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/policyoriented/policyText.json", method = RequestMethod.GET)
    public String policyText(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();

        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 2);
        model.put("menuId", 0);
        model.put("floatName", "热点双创政策");
        return "policyoriented/policyText";
    }

    /**
     * 政策导向---图解信息
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/policyoriented/policyImage.json", method = RequestMethod.GET)
    public String policyImage(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();

        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 2);
        model.put("menuId", 1);
        model.put("floatName", "热点双创政策");
        return "policyoriented/policyImage";
    }

    /**
     * 政策导向---视频信息
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/policyoriented/policyVideo.json", method = RequestMethod.GET)
    public String policyVideo(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();

        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 2);
        model.put("menuId", 2);
        model.put("floatName", "热点双创政策");
        return "policyoriented/policyVideo";
    }

    /**
     * 政策导向---影响信息
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/policyoriented/policyAffect.json", method = RequestMethod.GET)
    public String policyAffect(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();

        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 2);
        model.put("menuId", 3);
        model.put("floatName", "热点双创政策");
        return "policyoriented/policyAffect";
    }

    /**
     * 政策导向---具体报道内容分析
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/policyoriented/reportContent.json", method = RequestMethod.GET)
    public String reportContent(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();

        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 2);
        model.put("menuId", 4);
        model.put("floatName", "热点双创政策");
        return "policyoriented/reportContent";
    }

    /**
     * 政策导向---文章趋势
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/policyoriented/articleTrend.json", method = RequestMethod.GET)
    public String articleTrend(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();

        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 2);
        model.put("menuId", 5);
        model.put("floatName", "热点双创政策");
        return "policyoriented/articleTrend";
    }

    /**
     * 政策导向---参与性分析
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/policyoriented/mediaParticipationAnalysis.json", method = RequestMethod.GET)
    public String mediaParticipationAnalysis(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();

        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 2);
        model.put("menuId", 6);
        model.put("floatName", "热点双创政策");
        return "policyoriented/mediaParticipationAnalysis";
    }

    /**
     * 政策导向---专题事件时间轴
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/policyoriented/specialEventShaft.json", method = RequestMethod.GET)
    public String specialEventShaft(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();

        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 2);
        model.put("menuId", 7);
        model.put("floatName", "热点双创政策");
        return "policyoriented/specialEventShaft";
    }

    /**
     * 政策导向---热点关键词分析
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/policyoriented/hotKeyword.json", method = RequestMethod.GET)
    public String hotKeyword(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();

        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 2);
        model.put("menuId", 8);
        model.put("floatName", "热点双创政策");
        return "policyoriented/hotKeyword";
    }

    /**
     * 政策导向---热点关键词分析
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月27日
     */
    @RequestMapping(value = "/policyoriented/hotEvent.json", method = RequestMethod.GET)
    public String hotEvent(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();
        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 2);
        model.put("menuId", 9);
        model.put("floatName", "热点双创政策");
        return "policyoriented/hotEvent";
    }


    /**
     * 政策热点---区域热点分析
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月29日
     */
    @RequestMapping(value = "/policyhot/areaHot.json", method = RequestMethod.GET)
    public String areaHot(Map<String, Object> model) {
        model.put("modelId", 3);
        model.put("menuId", 0);
        return "policyhot/areaHot";
    }


    /**
     * 政策热点---关注渠道分析
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月29日
     */
    @RequestMapping(value = "/policyhot/focusChannel.json", method = RequestMethod.GET)
    public String focusChannel(Map<String, Object> model) {
        model.put("modelId", 3);
        model.put("menuId", 1);
        ConditionDTO cond = new ConditionDTO();
        cond.setDataType(SysConst.DataType.POLICY.getCode());
        cond.setReportType(1L);
        List<String> provinceList = policyHotTopicService.searchProvince(cond);
        model.put("provinceData", getSortProvince(provinceList));
        return "policyhot/focusChannel";
    }


    /**
     * 政策热点---媒体参与度
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月29日
     */
    @RequestMapping(value = "/policyhot/mediaParticipation.json", method = RequestMethod.GET)
    public String mediaParticipation(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();
        cond.setVectorType("1");
        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 3);
        model.put("menuId", 2);
        model.put("floatName", "媒体热点事件");
        ConditionDTO pcond = new ConditionDTO();
        pcond.setDataType(SysConst.DataType.POLICY.getCode());
        pcond.setVectorType("1");
        List<String> provinceList = policyHotTopicService.searchProvince(pcond);
        model.put("provinceData", getSortProvince(provinceList));
        return "policyhot/mediaParticipation";
    }

    /**
     * 政策热点---社会参与度
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月29日
     */
    @RequestMapping(value = "/policyhot/socialParticipation.json", method = RequestMethod.GET)
    public String socialParticipation(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();
        cond.setVectorType("2");
        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 3);
        model.put("menuId", 3);
        model.put("floatName", "社会热点事件");
        ConditionDTO pcond = new ConditionDTO();
        pcond.setDataType(SysConst.DataType.POLICY.getCode());
        pcond.setVectorType("2");
        List<String> provinceList = policyHotTopicService.searchProvince(pcond);
        model.put("provinceData", getSortProvince(provinceList));
        return "policyhot/socialParticipation";
    }


    /**
     * 政策热点---行业参与度
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月29日
     */
    @RequestMapping(value = "/policyhot/industryParticipation.json", method = RequestMethod.GET)
    public String industryParticipation(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();
        cond.setVectorType("3");
        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 3);
        model.put("menuId", 4);
        model.put("floatName", "行业热点事件");
        ConditionDTO pcond = new ConditionDTO();
        pcond.setDataType(SysConst.DataType.POLICY.getCode());
        pcond.setVectorType("3");
        List<String> provinceList = policyHotTopicService.searchProvince(pcond);
        model.put("provinceData", getSortProvince(provinceList));
        return "policyhot/industryParticipation";
    }

    /**
     * 政策效益---双创政策效益分析---投资效益分析
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月29日
     */
    @RequestMapping(value = "/policybenefit/policyBenefit.json", method = RequestMethod.GET)
    public String policyBenefit(Map<String, Object> model) {
        model.put("modelId", 4);
        model.put("menuId", 0);
        return "policybenefit/policyBenefit";
    }


    /**
     * 政策效益---双创政策效益分析---经济效益分析
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月29日
     */
    @RequestMapping(value = "/policybenefit/economicBenefit.json", method = RequestMethod.GET)
    public String economicBenefit(Map<String, Object> model) {
        model.put("modelId", 4);
        model.put("menuId", 1);
        return "policybenefit/economicBenefit";
    }


    /**
     * 政策效益---双创政策效益分析--公共服务指数
     * <p>Description: </p>
     *
     * @param model
     * @return
     * @author xiaobo
     * @date 2017年3月29日
     */
    @RequestMapping(value = "/policybenefit/publicBenefit.json", method = RequestMethod.GET)
    public String publicBenefit(Map<String, Object> model) {
        model.put("modelId", 4);
        model.put("menuId", 2);
        return "policybenefit/publicBenefit";
    }


    private List<String> getSortProvince(List<String> list) {
        List<String> newList = new ArrayList<String>();
        if (list != null && list.size() > 0) {
            Set<String> provinceSet = SysConst.getProvinceSet();
            for (String province : provinceSet) {
                if (list.contains(province)) {
                    newList.add(province);
                }
            }
        }
        if (newList.size() == 0) {
            newList.add("无");
        }
        return newList;
    }
}