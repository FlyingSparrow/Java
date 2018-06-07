package com.huishu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * WordCloud 路径配置
 * 线程数配置
 * @author wangjianchun
 */
@Component
@PropertySource(value = "classpath:analysis-config.properties")
public class AnalysisConfig {

    @Value("${analysis.source.more.path}")
    private String sourceMorePath;
    @Value("${analysis.source.less.path}")
    private String sourceLessPath;
    @Value("${analysis.transform.num}")
    private Integer transformNum;

    /**
     * 互联网关键词
     */
    @Value("${analysis.keywords.industry.internet}")
    private String internetKeyWords;
    /**
     * 交通关词
     */
    @Value("${analysis.keywords.industry.traffic}")
    private String trafficKeyWords;
    /**
     * 教育关键词
     */
    @Value("${analysis.keywords.industry.education}")
    private String educationKeyWords;
    /**
     * 旅游关键词
     */
    @Value("${analysis.keywords.industry.tourism}")
    private String tourismKeyWords;
    /**
     * 金融关键词
     */
    @Value("${analysis.keywords.industry.finance}")
    private String financeKeyWords;

    /**
     * 视频是否分析
     */
    @Value("${analysis.data.video.mark}")
    private boolean videoMark;
    @Value("${analysis.video.thread.num}")
    private Integer videoThreadNum;
    /**
     * 论坛是否分析
     */
    @Value("${analysis.data.forum.mark}")
    private boolean forumMark;
    @Value("${analysis.forum.thread.num}")
    private Integer forumThreadNum;
    /**
     * 新闻是否分析
     */
    @Value("${analysis.data.news.mark}")
    private boolean newsMark;
    @Value("${analysis.news.thread.num}")
    private Integer newsThreadNum;
    /**
     * 微信 标识 data.wecchat.mark
     */
    @Value("${analysis.data.wecchat.mark}")
    private boolean wechatMark;
    @Value("${analysis.wecchat.thread.num}")
    private Integer wechatThreadNum;
    /**
     * 招聘data.recruitment.mark=true
     */
    @Value("${analysis.data.recruitment.mark}")
    private boolean recruitmentMark;
    @Value("${analysis.recruitment.thread.num}")
    private Integer recruitmentThreadNum;
    /**
     * 投资 data.investment.mark=false
     */
    @Value("${analysis.data.investment.mark}")
    private boolean investmentMark;
    @Value("${analysis.investment.thread.num}")
    private Integer investmentThreadNum;
    /**
     * 政策
     */
    @Value("${analysis.data.policy.mark}")
    private boolean policyMark;
    @Value("${analysis.policy.thread.num}")
    private Integer policyThreadNum;
    /**
     * 综合
     */
    @Value("${analysis.data.zonghe.mark}")
    private boolean zongheMark;
    @Value("${analysis.zonghe.thread.num}")
    private Integer zongheThreadNum;

    /**
     * 并购
     */
    @Value("${analysis.data.merger.mark}")
    private boolean mergerMark;
    @Value("${analysis.merger.thread.num}")
    private Integer mergerThreadNum;
    /**
     * 退出
     */
    @Value("${analysis.data.quit.mark}")
    private boolean quitMark;
    @Value("${analysis.quit.thread.num}")
    private Integer quitThreadNum;

    /**
     * 工商数据
     */
    @Value("${analysis.data.industry.mark}")
    private boolean industryMark;
    @Value("${analysis.industry.thread.num}")
    private Integer industryThreadNum;

    /**
     * 教育水平
     */
    @Value("${analysis.recruitment.education}")
    private String recruitmentEdu;
    /**
     * 地域
     */
    @Value("${analysis.common.area}")
    private String commonArea;
    /**
     * 图片后缀
     */
    @Value("${analysis.picture.suffix}")
    private String pictureSuffix;
    @Value("${analysis.social.site}")
    private String socialSite;
    @Value("${analysis.center.department}")
    private String centerDepartment;

    /**
     * recruitment.internet.keywords=工程师,助理,互联网
     */
    @Value("${analysis.recruitment.internet.keywords}")
    private String recruitmentInternetKeywords;
    /**
     * recruitment.traffic.keywords=车,快递,运输,司机,进口,出口,贸易,航空,列车
     */
    @Value("${analysis.recruitment.traffic.keywords}")
    private String recruitmentTrafficKeywords;
    /**
     * recruitment.education.keywords=教师,助教
     */
    @Value("${analysis.recruitment.education.keywords}")
    private String recruitmentEducationKeywords;
    /**
     * recruitment.tourism.keywords=导游,旅游
     */
    @Value("${analysis.recruitment.tourism.keywords}")
    private String recruitmentTourismKeywords;
    /**
     * recruitment.finance.keywords=分析师,券商,银行,基金,保险,金融,理财
     */
    @Value("${analysis.recruitment.finance.keywords}")
    private String recruitmentFinanceKeywords;
    @Value("${analysis.invalid.imageurl}")
    private String invalidImageurl;
    @Value("${analysis.king.save.mark}")
    private boolean kingSaveMark;

    public String getSourceMorePath() {
        return sourceMorePath;
    }

    public String getSourceLessPath() {
        return sourceLessPath;
    }

    public Integer getTransformNum() {
        return transformNum;
    }

    public String getInternetKeyWords() {
        return internetKeyWords;
    }

    public String getTrafficKeyWords() {
        return trafficKeyWords;
    }

    public String getEducationKeyWords() {
        return educationKeyWords;
    }

    public String getTourismKeyWords() {
        return tourismKeyWords;
    }

    public String getFinanceKeyWords() {
        return financeKeyWords;
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

    public String getRecruitmentEdu() {
        return recruitmentEdu;
    }

    public String getCommonArea() {
        return commonArea;
    }

    public String getPictureSuffix() {
        return pictureSuffix;
    }

    public String getSocialSite() {
        return socialSite;
    }

    public String getCenterDepartment() {
        return centerDepartment;
    }

    public String getRecruitmentInternetKeywords() {
        return recruitmentInternetKeywords;
    }

    public String getRecruitmentTrafficKeywords() {
        return recruitmentTrafficKeywords;
    }

    public String getRecruitmentEducationKeywords() {
        return recruitmentEducationKeywords;
    }

    public String getRecruitmentTourismKeywords() {
        return recruitmentTourismKeywords;
    }

    public String getRecruitmentFinanceKeywords() {
        return recruitmentFinanceKeywords;
    }

    public String getInvalidImageurl() {
        return invalidImageurl;
    }

    public boolean isKingSaveMark() {
        return kingSaveMark;
    }
}
