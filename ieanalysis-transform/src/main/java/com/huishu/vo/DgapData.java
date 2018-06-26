package com.huishu.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * <p>Title: InvestmentData</p>
 * <p>Description: </p>
 *
 * @author xiaobo
 * @date 2017年3月21日
 * Double gen analysis platform
 */
public class DgapData implements Serializable {

    private static final long serialVersionUID = 8754743269121672297L;
    /**
     * 记录的唯一id
     */
    private String id;

    /**
     * 月份
     */
    private Long month;

    /**
     * 年份
     */
    private Long year;

    /**
     * 小时
     */
    private Long hour;

    /**
     * 天
     */
    private Long day;

    /**
     * 投资时间/发布时间	yyyy-mm-dd HH:mm:ss
     */
    private String time;

    /**
     * 数据类型	 数据类  1,政策; 2,招聘; 3,投资; 4,工商
     */
    private Long dataType;

    /**
     * 省份
     */
    private String province;

    /**
     * 地域
     */
    private String area;

    /**
     * 社会渠道(1,网络媒体,2,论坛,3,社交，4,外媒)
     */
    private String socialChannel;
    /**
     * 0,不是;1,是；
     */
    private Long hotEventMark;

    /**
     * 0,负面;1,中性;2,正面
     */
    private Long emotionMark;

    /**
     * 阅读量
     */
    private Long readNum;

    /**
     * 行业
     */
    private String industry;

    //投资数据
    /**
     * 公司全称
     */
    private String companyName;

    /**
     * 融资金额
     */
    private Double financingAmount;

    /**
     * 并购金额
     */
    private Double mergersAmount;

    /**
     * 退出金额
     */
    private Double quitAmount;

    //招聘
    /**
     * 岗位数
     */
    private Long jobsNumber;

    /**
     * 岗位名称
     */
    private String jobsName;

    /**
     * 岗位薪酬
     */
    private Long jobsRemuneration;

    /**
     * 双高人才   0不是   1 是
     */
    private Long jobsTalentMark;
    //经营环境
    /**
     * 发布类型    1,中央，2,地方，3,知识产权保护诉讼
     */
    private Long publishType;

    /**
     * 发布部门
     */
    private String publishDepartment;

    /**
     * 报道量
     */
    private Long reportNum;

    /**
     * 点击量
     */
    private Long hitNum;

    //政策导向
    /**
     * 政策标题
     */
    private String policyTitle;

    /**
     * 发布机构
     */
    private String policyReleaseMechanism;

    /**
     * 发文字号
     */
    private String policyPostShopName;

    /**
     * 发布人
     */
    private String policyPublishAuthor;

    /**
     * 0 文章；1，image；2video
     */
    private Long policyInfoType;

    /**
     * imageurl
     */
    private String policyImageUrl;

    /**
     * url
     */
    private String policyUrl;

    /**
     * 网站
     */
    private String site;

    /**
     * 内容
     */
    private String content;

    //具体报道分析
    /**
     * 1,认证用户;2, 普通用户
     */
    private Long socialType;

    /**
     * 1,媒体;2,社交;
     */
    private Long reportType;



    /**
     * 工商数据 开始
     */

    /**
     * 企业名称
     */
    private String enterName;
    /**
     * 公司状态
     */
    private String status;
    /**
     * 企业真正名称
     */
    private String realName;
    /**
     * 经营范围
     */
    private String businessScope;
    /**
     * 企业所有者
     */
    private String owner;
    /**
     * 注册资本
     */
    private String registeredCapital;
    /**
     * 注册时间
     */
    private String createDate;
    /**
     * 联系电话
     */
    private String tender;
    /**
     * 公司类型
     */
    private String companyType;
    /**
     * 软件著作权数量
     */
    private Integer softwareCopyright;
    /**
     * 专利数量
     */
    private Integer patents;
    /**
     * 融资次数
     */
    private Integer financingTime;
    /**
     * 融资时间，格式：yyyy-MM-dd
     */
    private String financingRaund;
    /**
     * 公司融资金额说明
     */
    private String companyFinancingAmount;
    /**
     * 员工人数
     */
    private String employees;
    /**
     * 对外投资次数
     */
    private Integer invest;
    /**
     * 地址
     */
    private String address;
    /**
     * 公司所属行业
     */
    private String companyIndustry;
    /**
     * 分支机构数量
     */
    private Integer branchCount;
    /**
     * 股东
     */
    private String shareholder;
    /**
     * 数据来源，天眼查，企查查等
     */
    private String source;

    /**
     * 工商数据 结束
     */

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

    public String getEnterName() {
        return enterName;
    }

    public void setEnterName(String enterName) {
        this.enterName = enterName;
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

    public String getTender() {
        return tender;
    }

    public void setTender(String tender) {
        this.tender = tender;
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

    public Integer getFinancingTime() {
        return financingTime;
    }

    public void setFinancingTime(Integer financingTime) {
        this.financingTime = financingTime;
    }

    public String getFinancingRaund() {
        return financingRaund;
    }

    public void setFinancingRaund(String financingRaund) {
        this.financingRaund = financingRaund;
    }

    public String getCompanyFinancingAmount() {
        return companyFinancingAmount;
    }

    public void setCompanyFinancingAmount(String companyFinancingAmount) {
        this.companyFinancingAmount = companyFinancingAmount;
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

    public String getCompanyIndustry() {
        return companyIndustry;
    }

    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

