package com.huishu.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 企业工商数据
 *
 * @author wangjianchun
 * @date 2018-6-6
 */
@Entity
@Table(name = "industry_bak")
public class IndustryDataBak implements Serializable {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    /**
     * 企业名称
     */
    @Column(name = "EnterpriseName")
    private String enterpriseName;
    /**
     * 公司状态
     */
    @Column(name = "Status")
    private String status;
    /**
     * 企业真正名称
     */
    @Column(name = "RealName")
    private String realName;
    /**
     * 经营范围
     */
    @Column(name = "BusinessScope")
    private String businessScope;
    /**
     * 企业所有者
     */
    @Column(name = "Owner")
    private String owner;
    /**
     * 注册资本
     */
    @Column(name = "RegisteredCapital")
    private String registeredCapital;
    /**
     * 注册时间
     */
    @Column(name = "CreateDate")
    private String createDate;
    /**
     * 联系电话
     */
    @Column(name = "ContactNumber")
    private String contactNumber;
    /**
     * 公司类型
     */
    @Column(name = "CompanyType")
    private String companyType;
    /**
     * 软件著作权数量
     */
    @Column(name = "SoftwareCopyright")
    private Integer softwareCopyright;
    /**
     * 专利数量
     */
    @Column(name = "Patents")
    private Integer patents;
    /**
     * 融资次数
     */
    @Column(name = "FinancingRound")
    private Integer financingRound;
    /**
     * 融资时间，格式：yyyy-MM-dd
     */
    @Column(name = "FinancingTime")
    private String financingTime;
    /**
     * 融资金额说明
     */
    @Column(name = "FinancingAmount")
    private Double financingAmount;
    /**
     * 员工人数
     */
    @Column(name = "Employees")
    private Integer employees;
    /**
     * 对外投资次数
     */
    @Column(name = "Invest")
    private Integer invest;
    /**
     * 地址
     */
    @Column(name = "Address")
    private String address;
    /**
     * 行业
     */
    @Column(name = "Industry")
    private String industry;
    /**
     * 分支机构数量
     */
    @Column(name = "branchCount")
    private Integer branchCount;
    /**
     * 股东
     */
    @Column(name = "shareholder")
    private String shareholder;
    /**
     * 数据来源，天眼查，企查查等
     */
    @Column(name = "source")
    private String source;

    @Column(name = "biaoshi")
    private String biaoShi;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getFinancingTime() {
        return financingTime;
    }

    public void setFinancingTime(String financingTime) {
        this.financingTime = financingTime;
    }

    public Integer getFinancingRound() {
        return financingRound;
    }

    public void setFinancingRound(Integer financingRound) {
        this.financingRound = financingRound;
    }

    public Double getFinancingAmount() {
        return financingAmount;
    }

    public void setFinancingAmount(Double financingAmount) {
        this.financingAmount = financingAmount;
    }

    public Integer getEmployees() {
        return employees;
    }

    public void setEmployees(Integer employees) {
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

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
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

    public String getBiaoShi() {
        return biaoShi;
    }

    public void setBiaoShi(String biaoShi) {
        this.biaoShi = biaoShi;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
