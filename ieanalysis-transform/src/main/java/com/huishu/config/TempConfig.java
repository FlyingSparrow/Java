package com.huishu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * WordCloud 路径配置
 * 线程数配置
 *
 * @author wangjianchun
 */
@Component
@PropertySource("classpath:temp.properties")
public class TempConfig {

    @Value("${wechat}")
    private String wechatMark;
    @Value("${forum}")
    private String forumMark;
    @Value("${news}")
    private String newsMark;
    @Value("${recruitment}")
    private String recruitmentMark;
    @Value("${investment}")
    private String investmentMark;
    @Value("${merger}")
    private String mergerMark;
    @Value("${quit}")
    private String quitMark;
    @Value("${industry}")
    private String industryMark;
    @Value("${video}")
    private String videoMark;
    @Value("${policy}")
    private String policyMark;
    @Value("${zonghe}")
    private String zongheMark;

    public String getWechatMark() {
        return wechatMark;
    }

    public String getForumMark() {
        return forumMark;
    }

    public String getNewsMark() {
        return newsMark;
    }

    public String getRecruitmentMark() {
        return recruitmentMark;
    }

    public String getInvestmentMark() {
        return investmentMark;
    }

    public String getMergerMark() {
        return mergerMark;
    }

    public String getQuitMark() {
        return quitMark;
    }

    public String getIndustryMark() {
        return industryMark;
    }

    public String getVideoMark() {
        return videoMark;
    }

    public String getPolicyMark() {
        return policyMark;
    }

    public String getZongheMark() {
        return zongheMark;
    }

}
