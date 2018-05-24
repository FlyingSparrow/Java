package com.huishu.ieanalysis.es.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * <p>Title: InvestmentData</p>
 * <p>Description: </p>
 *
 * @author xiaobo
 * @date 2017年3月21日
 * Double gen analysis platform
 */
@Document(indexName = "dgap", type = "info", shards = 5, replicas = 1, refreshInterval = "-1")
public class DgapData implements Serializable {

    private static final long serialVersionUID = 8754743269121672297L;
    //记录的唯一id
    @Id
    private String id;
    //月份
    @Field(type = FieldType.Long)
    private Long month;
    //年份
    @Field(type = FieldType.Long)
    private Long year;
    //小时
    @Field(type = FieldType.Long)
    private Long hour;
    //天
    @Field(type = FieldType.Long)
    private Long day;
    //投资时间/发布时间	yyyy-mm-dd HH:mm:ss
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String time;
    //数据类型	 数据类  1,政策; 2,招聘; 3,投资; 4,工商
    @Field(type = FieldType.Long)
    private Long dataType;
    //省份
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String province;
    //地域
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String area;
    //社会渠道(1,网络媒体,2,论坛,3,社交，4,外媒)
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String socialChannel;
    //0,不是;1,是；
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed)
    private Long hotEventMark;
    //0,负面;1,中性;2,正面
    @Field(type = FieldType.Long)
    private Long emotionMark;
    // 阅读量
    @Field(type = FieldType.Long)
    private Long readNum;
    //行业
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String industry;

    //投资数据
    //公司全称
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String companyName;
    //融资金额
    @Field(type = FieldType.Double, index = FieldIndex.not_analyzed)
    private Double financingAmount;
    //并购金额
    @Field(type = FieldType.Double, index = FieldIndex.not_analyzed)
    private Double mergersAmount;
    //退出金额
    @Field(type = FieldType.Double, index = FieldIndex.not_analyzed)
    private Double quitAmount;

    //招聘
    //岗位数
    @Field(type = FieldType.Long)
    private Long jobsNumber;
    //岗位名称
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String jobsName;
    //岗位薪酬
    @Field(type = FieldType.Long)
    private Long jobsRemuneration;
    //双高人才   0不是   1 是
    @Field(type = FieldType.Long)
    private Long jobsTalentMark;

    //经营环境
    //发布类型    1,中央，2,地方，3,知识产权保护诉讼
    @Field(type = FieldType.Long)
    private Long publishType;
    //发布部门
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String publishDepartment;
    //报道量
    @Field(type = FieldType.Long)
    private Long reportNum;
    //点击量
    @Field(type = FieldType.Long)
    private Long hitNum;

    //政策导向
    //政策标题
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String policyTitle;
    //发布机构
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String policyReleaseMechanism;
    //发文字号
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String policyPostShopName;
    //发布人
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String policyPublishAuthor;
    //0 文章；1，image；2video
    @Field(type = FieldType.Long)
    private Long policyInfoType;
    //imageurl
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String policyImageUrl;
    //url
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String policyUrl;
    //网站
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String site;
    //内容
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String content;

    //具体报道分析
    // 1,认证用户;2, 普通用户
    @Field(type = FieldType.Long)
    private Long socialType;
    //1,媒体;2,社交;
    @Field(type = FieldType.Long)
    private Long reportType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getDataType() {
        return dataType;
    }

    public void setDataType(Long dataType) {
        this.dataType = dataType;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Double getFinancingAmount() {
        return financingAmount;
    }

    public void setFinancingAmount(Double financingAmount) {
        this.financingAmount = financingAmount;
    }

    public Double getMergersAmount() {
        return mergersAmount;
    }

    public void setMergersAmount(Double mergersAmount) {
        this.mergersAmount = mergersAmount;
    }

    public Double getQuitAmount() {
        return quitAmount;
    }

    public void setQuitAmount(Double quitAmount) {
        this.quitAmount = quitAmount;
    }

    public Long getJobsNumber() {
        return jobsNumber;
    }

    public void setJobsNumber(Long jobsNumber) {
        this.jobsNumber = jobsNumber;
    }

    public String getJobsName() {
        return jobsName;
    }

    public void setJobsName(String jobsName) {
        this.jobsName = jobsName;
    }

    public Long getJobsRemuneration() {
        return jobsRemuneration;
    }

    public void setJobsRemuneration(Long jobsRemuneration) {
        this.jobsRemuneration = jobsRemuneration;
    }

    public Long getJobsTalentMark() {
        return jobsTalentMark;
    }

    public void setJobsTalentMark(Long jobsTalentMark) {
        this.jobsTalentMark = jobsTalentMark;
    }

    public Long getPublishType() {
        return publishType;
    }

    public void setPublishType(Long publishType) {
        this.publishType = publishType;
    }

    public String getPublishDepartment() {
        return publishDepartment;
    }

    public void setPublishDepartment(String publishDepartment) {
        this.publishDepartment = publishDepartment;
    }

    public Long getReportNum() {
        return reportNum;
    }

    public void setReportNum(Long reportNum) {
        this.reportNum = reportNum;
    }


    public String getPolicyTitle() {
        return policyTitle;
    }

    public void setPolicyTitle(String policyTitle) {
        this.policyTitle = policyTitle;
    }

    public String getPolicyReleaseMechanism() {
        return policyReleaseMechanism;
    }

    public void setPolicyReleaseMechanism(String policyReleaseMechanism) {
        this.policyReleaseMechanism = policyReleaseMechanism;
    }

    public String getPolicyPostShopName() {
        return policyPostShopName;
    }

    public void setPolicyPostShopName(String policyPostShopName) {
        this.policyPostShopName = policyPostShopName;
    }

    public String getPolicyPublishAuthor() {
        return policyPublishAuthor;
    }

    public void setPolicyPublishAuthor(String policyPublishAuthor) {
        this.policyPublishAuthor = policyPublishAuthor;
    }

    public Long getEmotionMark() {
        return emotionMark;
    }

    public void setEmotionMark(Long emotionMark) {
        this.emotionMark = emotionMark;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Long getHotEventMark() {
        return hotEventMark;
    }

    public void setHotEventMark(Long hotEventMark) {
        this.hotEventMark = hotEventMark;
    }

    public Long getHour() {
        return hour;
    }

    public void setHour(Long hour) {
        this.hour = hour;
    }

    public String getSocialChannel() {
        return socialChannel;
    }

    public void setSocialChannel(String socialChannel) {
        this.socialChannel = socialChannel;
    }


    public String getPolicyUrl() {
        return policyUrl;
    }

    public void setPolicyUrl(String policyUrl) {
        this.policyUrl = policyUrl;
    }

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    public Long getPolicyInfoType() {
        return policyInfoType;
    }

    public void setPolicyInfoType(Long policyInfoType) {
        this.policyInfoType = policyInfoType;
    }

    public String getPolicyImageUrl() {
        return policyImageUrl;
    }

    public void setPolicyImageUrl(String policyImageUrl) {
        this.policyImageUrl = policyImageUrl;
    }

    public Long getSocialType() {
        return socialType;
    }

    public void setSocialType(Long socialType) {
        this.socialType = socialType;
    }

    public Long getReportType() {
        return reportType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setReportType(Long reportType) {
        this.reportType = reportType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getReadNum() {
        return readNum;
    }

    public void setReadNum(Long readNum) {
        this.readNum = readNum;
    }

    public Long getHitNum() {
        return hitNum;
    }

    public void setHitNum(Long hitNum) {
        this.hitNum = hitNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

