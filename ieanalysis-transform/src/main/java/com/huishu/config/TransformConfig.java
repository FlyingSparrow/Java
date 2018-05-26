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
@PropertySource("classpath:transform.properties")
public class TransformConfig {

    @Value("${transform.num}")
    private Integer transformNum;

    /**
     * 视频是否分析
     */
    @Value("${data.video.mark}")
    private boolean videoMark;
    @Value("${video.thread.num}")
    private Integer videoThreadNum;
    /**
     * 论坛是否分析
     */
    @Value("${data.forum.mark}")
    private boolean forumMark;
    @Value("${forum.thread.num}")
    private Integer forumThreadNum;
    /**
     * 新闻是否分析
     */
    @Value("${data.news.mark}")
    private boolean newsMark;
    @Value("${news.thread.num}")
    private Integer newsThreadNum;
    /**
     * 微信 标识 data.wecchat.mark
     */
    @Value("${data.wecchat.mark}")
    private boolean wechatMark;
    @Value("${wecchat.thread.num}")
    private Integer wechatThreadNum;
    /**
     * 招聘data.recruitment.mark=true
     */
    @Value("${data.recruitment.mark}")
    private boolean recruitmentMark;
    @Value("${recruitment.thread.num}")
    private Integer recruitmentThreadNum;
    /**
     * 投资 data.investment.mark=false
     */
    @Value("${data.investment.mark}")
    private boolean investmentMark;
    @Value("${investment.thread.num}")
    private Integer investmentThreadNum;
    /**
     * 政策
     */
    @Value("${data.policy.mark}")
    private boolean policyMark;
    @Value("${policy.thread.num}")
    private Integer policyThreadNum;
    /**
     * 综合
     */
    @Value("${data.zonghe.mark}")
    private boolean zongheMark;
    @Value("${zonghe.thread.num}")
    private Integer zongheThreadNum;

    /**
     * 并购
     */
    @Value("${data.merger.mark}")
    private boolean mergerMark;
    @Value("${merger.thread.num}")
    private Integer mergerThreadNum;
    /**
     * 退出
     */
    @Value("${data.quit.mark}")
    private boolean quitMark;
    @Value("${quit.thread.num}")
    private Integer quitThreadNum;

    /**
     * 工商数据
     */
    @Value("${data.industry.mark}")
    private boolean industryMark;
    @Value("${industry.thread.num}")
    private Integer industryThreadNum;

    public Integer getTransformNum() {
        return transformNum;
    }

    public boolean isVideoMark() {
        return videoMark;
    }

    public Integer getVideoThreadNum() {
        return videoThreadNum;
    }

    public boolean isForumMark() {
        return forumMark;
    }

    public Integer getForumThreadNum() {
        return forumThreadNum;
    }

    public boolean isNewsMark() {
        return newsMark;
    }

    public Integer getNewsThreadNum() {
        return newsThreadNum;
    }

    public boolean isWechatMark() {
        return wechatMark;
    }

    public Integer getWechatThreadNum() {
        return wechatThreadNum;
    }

    public boolean isRecruitmentMark() {
        return recruitmentMark;
    }

    public Integer getRecruitmentThreadNum() {
        return recruitmentThreadNum;
    }

    public boolean isInvestmentMark() {
        return investmentMark;
    }

    public Integer getInvestmentThreadNum() {
        return investmentThreadNum;
    }

    public boolean isPolicyMark() {
        return policyMark;
    }

    public Integer getPolicyThreadNum() {
        return policyThreadNum;
    }

    public boolean isZongheMark() {
        return zongheMark;
    }

    public Integer getZongheThreadNum() {
        return zongheThreadNum;
    }

    public boolean isMergerMark() {
        return mergerMark;
    }

    public Integer getMergerThreadNum() {
        return mergerThreadNum;
    }

    public boolean isQuitMark() {
        return quitMark;
    }

    public Integer getQuitThreadNum() {
        return quitThreadNum;
    }

    public boolean isIndustryMark() {
        return industryMark;
    }

    public Integer getIndustryThreadNum() {
        return industryThreadNum;
    }
}
