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

    /**
     * 工商数据 开始
     */

    /**
     * 企业名称
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String enterpriseName;
    /**
     * 公司状态
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String status;
    /**
     * 企业真正名称
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String realName;
    /**
     * 经营范围
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String businessScope;
    /**
     * 企业所有者
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String owner;
    /**
     * 注册资本
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String registeredCapital;
    /**
     * 注册时间
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String createDate;
    /**
     * 联系电话
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String contactNumber;
    /**
     * 公司类型
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String companyType;
    /**
     * 软件著作权数量
     */
    @Field(type = FieldType.Integer)
    private Integer softwareCopyright;
    /**
     * 专利数量
     */
    @Field(type = FieldType.Integer)
    private Integer patents;
    /**
     * 融资次数
     */
    @Field(type = FieldType.Integer)
    private Integer financingRound;
    /**
     * 融资时间，格式：yyyy-MM-dd
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String financingTime;
    /**
     * 员工人数
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String employees;
    /**
     * 对外投资次数
     */
    @Field(type = FieldType.Integer)
    private Integer invest;
    /**
     * 地址
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String address;
    /**
     * 分支机构数量
     */
    @Field(type = FieldType.Integer)
    private Integer branchCount;
    /**
     * 股东
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String shareholder;
    /**
     * 数据来源，天眼查，企查查等
     */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String source;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Long getHour() {
        return hour;
    }

    public void setHour(Long hour) {
        this.hour = hour;
    }

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSocialChannel() {
        return socialChannel;
    }

    public void setSocialChannel(String socialChannel) {
        this.socialChannel = socialChannel;
    }

    public Long getHotEventMark() {
        return hotEventMark;
    }

    public void setHotEventMark(Long hotEventMark) {
        this.hotEventMark = hotEventMark;
    }

    public Long getEmotionMark() {
        return emotionMark;
    }

    public void setEmotionMark(Long emotionMark) {
        this.emotionMark = emotionMark;
    }

    public Long getReadNum() {
        return readNum;
    }

    public void setReadNum(Long readNum) {
        this.readNum = readNum;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Long getHitNum() {
        return hitNum;
    }

    public void setHitNum(Long hitNum) {
        this.hitNum = hitNum;
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

    public String getPolicyUrl() {
        return policyUrl;
    }

    public void setPolicyUrl(String policyUrl) {
        this.policyUrl = policyUrl;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public void setReportType(Long reportType) {
        this.reportType = reportType;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public Integer getSoftwareCopyright() {
        return softwareCopyright;
    }

    public void setSoftwareCopyright(Integer softwareCopyright) {
        this.softwareCopyright = softwareCopyright;
    }

    public Integer getPatents() {
        return patents;
    }

    public void setPatents(Integer patents) {
        this.patents = patents;
    }

    public Integer getFinancingRound() {
        return financingRound;
    }

    public void setFinancingRound(Integer financingRound) {
        this.financingRound = financingRound;
    }

    public String getFinancingTime() {
        return financingTime;
    }

    public void setFinancingTime(String financingTime) {
        this.financingTime = financingTime;
    }

    public String getEmployees() {
        return employees;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }

    public Integer getInvest() {
        return invest;
    }

    public void setInvest(Integer invest) {
        this.invest = invest;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBranchCount() {
        return branchCount;
    }

    public void setBranchCount(Integer branchCount) {
        this.branchCount = branchCount;
    }

    public String getShareholder() {
        return shareholder;
    }

    public void setShareholder(String shareholder) {
        this.shareholder = shareholder;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 工商数据 结束
     */



    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

