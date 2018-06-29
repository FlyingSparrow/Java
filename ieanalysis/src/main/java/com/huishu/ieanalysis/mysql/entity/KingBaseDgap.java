package com.huishu.ieanalysis.mysql.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author wangjianchun
 */
@Entity
@Table(name = "DGAP_DATA")
public class KingBaseDgap implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 8236621469582361339L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    @Column(name = "TIME")
    private String time;
    //数据类型  1,政策; 2,招聘; 3,投资; 4,工商
    @Column(name = "DATA_TYPE")
    private Long dataType;
    //省份
    @Column(name = "PROVINCE")
    private String province;
    //社会渠道(1,网络媒体,2,论坛,3,社交)
    @Column(name = "SOCIAL_CHANNEL")
    private String socialChannel;
    //0,不是;1,是；
    @Column(name = "HOT_EVENT_MARK")
    private Long hotEventMark;
    //0,负面;1,中性;2,正面
    @Column(name = "EMOTION_MARK")
    private Long emotionMark;
    //点击量/阅读量
    @Column(name = "READ_NUM")
    private Long readNum;
    //行业
    @Column(name = "INDUSTRY")
    private String industry;

    //投资数据
    //公司名称
    @Column(name = "COMPANY_NAME")
    private String companyName;
    //融资金额
    @Column(name = "FINANCING_AMOUNT")
    private Double financingAmount;
    //并购金额
    @Column(name = "MERGERS_AMOUNT")
    private Double mergersAmount;
    //退出金额
    @Column(name = "QUIT_AMOUNT")
    private Double quitAmount;

    //招聘
    //岗位数
    @Column(name = "JOBS_NUMBER")
    private Long jobsNumber;
    //岗位名称
    @Column(name = "JOBS_NAME")
    private String jobsName;
    //岗位薪酬
    @Column(name = "JOBS_REMUNERATION")
    private Long jobsRemuneration;
    //双高人才   0不是   1 是
    @Column(name = "JOBS_TALENT_MARK")
    private Long jobsTalentMark;

    //经营环境
    //发布类型   1中央,2地方,3诉讼, 4新闻,5综合 ; (固定)6论坛,7招聘 ,8微信,9投资,10工商,11视频 ,12其它,13,投资并购，14投资退出
    @Column(name = "PUBLISH_TYPE")
    private Long publishType;
    //发布部门
    @Column(name = "PUBLISH_DEPARTMENT")
    private String publishDepartment;
    //报道量
    @Column(name = "REPORT_NUM")
    private Long reportNum;
    //关注量
    @Column(name = "HIT_NUM")
    private Long hitNum;

    //政策导向
    //政策标题
    @Column(name = "POLICY_TITLE")
    private String policyTitle;
    //发布机构
    @Column(name = "POLICY_RELEASE_MECHANISM")
    private String policyReleaseMechanism;
    //发文字号
    @Column(name = "POLICY_POST_SHOP_NAME")
    private String policyPostShopName;
    //发布人
    @Column(name = "POLICY_PUBLISH_AUTHOR")
    private String policyPublishAuthor;
    //0 文章；1，image；2video
    @Column(name = "POLICY_INFO_TYPE")
    private Long policyInfoType;
    //imageurl
    @Column(name = "POLICY_IMAGE_URL")
    private String policyImageUrl;

    //@Lob
    //url
    @Column(name = "POLICY_URL")
    private String policyUrl;
    //网站
    @Column(name = "SITE")
    private String site;

    //@Lob
    //网站
    @Column(name = "CONTENT")
    private String content;
    //1,媒体;2,社交;
    @Column(name = "REPORT_TYPE")
    private Long reportType;


    /**
     * 工商数据 开始
     */

    /**
     * 企业名称
     */
    @Column(name = "ENTERPRISE_NAME")
    private String enterpriseName;
    /**
     * 公司状态
     */
    @Column(name = "STATUS")
    private String status;
    /**
     * 企业真正名称
     */
    @Column(name = "REAL_NAME")
    private String realName;
    /**
     * 经营范围
     */
    @Column(name = "BUSINESS_SCOPE")
    private String businessScope;
    /**
     * 企业所有者
     */
    @Column(name = "OWNER")
    private String owner;
    /**
     * 注册资本
     */
    @Column(name = "REGISTERED_CAPITAL")
    private String registeredCapital;
    /**
     * 注册时间
     */
    @Column(name = "CREATE_DATE")
    private String createDate;
    /**
     * 联系电话
     */
    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;
    /**
     * 公司类型
     */
    @Column(name = "COMPANY_TYPE")
    private String companyType;
    /**
     * 软件著作权数量
     */
    @Column(name = "SOFTWARE_COPYRIGHT")
    private Integer softwareCopyright;
    /**
     * 专利数量
     */
    @Column(name = "PATENTS")
    private Integer patents;
    /**
     * 融资次数
     */
    @Column(name = "FINANCING_ROUND")
    private Integer financingRound;
    /**
     * 融资时间，格式：yyyy-MM-dd
     */
    @Column(name = "FINANCING_TIME")
    private String financingTime;
    /**
     * 公司融资金额说明
     */
    @Column(name = "COMPANY_FINANCING_AMOUNT")
    private String companyFinancingAmount;
    /**
     * 员工人数
     */
    @Column(name = "EMPLOYEES")
    private String employees;
    /**
     * 对外投资次数
     */
    @Column(name = "INVEST")
    private Integer invest;
    /**
     * 地址
     */
    @Column(name = "ADDRESS")
    private String address;
    /**
     * 分支机构数量
     */
    @Column(name = "BRANCH_COUNT")
    private Integer branchCount;
    /**
     * 股东
     */
    @Column(name = "SHAREHOLDER")
    private String shareholder;
    /**
     * 数据来源，天眼查，企查查等
     */
    @Column(name = "SOURCE")
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
