package com.huishu.ieanalysis.controller;

import com.huishu.base.controller.BaseController;
import com.huishu.ieanalysis.constants.SysConst;
import com.huishu.ieanalysis.dto.ConditionDTO;
import com.huishu.ieanalysis.es.entity.DgapData;
import com.huishu.ieanalysis.es.service.PolicyHotTopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private PolicyHotTopicService policyHotTopicService;

    @RequestMapping("/")
    public String login(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();
        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 0);
        model.put("menuId", 0);
        model.put("floatName", "热点双创政策");
        return "energypolicy/investmentAnalysis";
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
    @RequestMapping("/energypolicy/investmentAnalysis.json")
    public String investmentAnalysis(Map<String, Object> model) {
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
    @RequestMapping("/energypolicy/registerAmountAnalysis.json")
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
    @RequestMapping("/energypolicy/registerRateAnalysis.json")
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
    @RequestMapping("/policyindex/capitalIndexAnalysis.json")
    public String capitalIndexAnalysis(Map<String, Object> model) {
        model.put("modelId", 1);
        model.put("menuId", 0);
        ConditionDTO cond = new ConditionDTO();
        cond.setDataType(SysConst.DATATYPE_INVESTMENT);
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
    @RequestMapping("/policyindex/talentIndexAnalysis.json")
    public String talentIndexAnalysis(Map<String, Object> model) {
        model.put("modelId", 1);
        model.put("menuId", 1);
        ConditionDTO cond = new ConditionDTO();
        cond.setDataType(SysConst.DATATYPE_RECRUITMENT);
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
    @RequestMapping("/policyindex/healthDegreeAnalysis.json")
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
    @RequestMapping("/policyindex/managementEnvironmentAnalysis.json")
    public String managementEnvironmentAnalysis(Map<String, Object> model) {
        model.put("modelId", 1);
        model.put("menuId", 3);
        ConditionDTO cond = new ConditionDTO();
        cond.setDataType(SysConst.DATATYPE_POLICY);
        List<String> list = new ArrayList<String>();
        list.add(SysConst.PUBLISHTYPE_CENTER);
        list.add(SysConst.PUBLISHTYPE_LOCAL);
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
    @RequestMapping("/policyindex/livenessAnalysis.json")
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
    @RequestMapping("/policyoriented/policyText.json")
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
    @RequestMapping("/policyoriented/policyImage.json")
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
    @RequestMapping("/policyoriented/policyVideo.json")
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
    @RequestMapping("/policyoriented/policyAffect.json")
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
    @RequestMapping("/policyoriented/reportContent.json")
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
    @RequestMapping("/policyoriented/articleTrend.json")
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
    @RequestMapping("/policyoriented/mediaParticipationAnalysis.json")
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
    @RequestMapping("/policyoriented/specialEventShaft.json")
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
    @RequestMapping("/policyoriented/hotKeyword.json")
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
    @RequestMapping("/policyoriented/hotEvent.json")
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
    @RequestMapping("/policyhot/areaHot.json")
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
    @RequestMapping("/policyhot/focusChannel.json")
    public String focusChannel(Map<String, Object> model) {
        model.put("modelId", 3);
        model.put("menuId", 1);
        ConditionDTO cond = new ConditionDTO();
        cond.setDataType(SysConst.DATATYPE_POLICY);
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
    @RequestMapping("/policyhot/mediaParticipation.json")
    public String mediaParticipation(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();
        cond.setVectorType("1");
        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 3);
        model.put("menuId", 2);
        model.put("floatName", "媒体热点事件");
        ConditionDTO pcond = new ConditionDTO();
        pcond.setDataType(SysConst.DATATYPE_POLICY);
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
    @RequestMapping("/policyhot/socialParticipation.json")
    public String socialParticipation(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();
        cond.setVectorType("2");
        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 3);
        model.put("menuId", 3);
        model.put("floatName", "社会热点事件");
        ConditionDTO pcond = new ConditionDTO();
        pcond.setDataType(SysConst.DATATYPE_POLICY);
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
    @RequestMapping("/policyhot/industryParticipation.json")
    public String industryParticipation(Map<String, Object> model) {
        ConditionDTO cond = new ConditionDTO();
        cond.setVectorType("3");
        Page<DgapData> page = policyHotTopicService.searchPolicyHotTopicInfo(cond);
        model.put("datas", page.getContent());
        model.put("modelId", 3);
        model.put("menuId", 4);
        model.put("floatName", "行业热点事件");
        ConditionDTO pcond = new ConditionDTO();
        pcond.setDataType(SysConst.DATATYPE_POLICY);
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
    @RequestMapping("/policybenefit/policyBenefit.json")
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
    @RequestMapping("/policybenefit/economicBenefit.json")
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
    @RequestMapping("/policybenefit/publicBenefit.json")
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