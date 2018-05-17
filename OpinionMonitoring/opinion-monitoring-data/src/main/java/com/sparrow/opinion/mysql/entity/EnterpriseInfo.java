package com.sparrow.opinion.mysql.entity;

import com.sparrow.opinion.base.bean.BasePageRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: EnterpriseInfo</p>
 * <p>Description: 企业信息实体类</p>
 *
 * @author zhangtong
 * @date 2017年6月19日
 */
@Entity
@Table(name = "t_enterprise_info")
public class EnterpriseInfo extends BasePageRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9201034849892179274L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "enterprise_name", nullable = false)
    private String enterpriseName;//企业名称（全称）

    @Column(name="registered_capital")
    private String registeredCapital;//注册资本

    @Column(name="shareholder")
    private String shareholder;//股东

    @Column(name="foreign_investment")
    private String foreignInvestment;//对外投资

    @Column(name = "business_scope", nullable = false)
    private String businessScope;//经营范围

    @Column(name="director_senior_supervisor")
    private String directorSeniorSupervisor;//董高监（董事、高层管理人员、监事）

    @Column(name = "legal_person", nullable = false)
    private String legalPerson;//法人

    @Column(name = "enterprise_abbreviation", nullable = false)
    private String enterpriseAbbreviation;//公司简称

    @Column(name = "created_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate; // 创建时间

    @Column(name = "created_user", nullable = false)
    private String createdUser; // 创建人

    @Column(name = "modified_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedDate; // 更新时间

    @Column(name = "modified_user", nullable = false)
    private String modifiedUser; // 更新人

    @Transient
    private List<Investmenter> investmenterList;//投资人

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getShareholder() {
        return shareholder;
    }

    public void setShareholder(String shareholder) {
        this.shareholder = shareholder;
    }

    public String getForeignInvestment() {
        return foreignInvestment;
    }

    public void setForeignInvestment(String foreignInvestment) {
        this.foreignInvestment = foreignInvestment;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getDirectorSeniorSupervisor() {
        return directorSeniorSupervisor;
    }

    public void setDirectorSeniorSupervisor(String directorSeniorSupervisor) {
        this.directorSeniorSupervisor = directorSeniorSupervisor;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getEnterpriseAbbreviation() {
        return enterpriseAbbreviation;
    }

    public void setEnterpriseAbbreviation(String enterpriseAbbreviation) {
        this.enterpriseAbbreviation = enterpriseAbbreviation;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public List<Investmenter> getInvestmenterList() {
        return investmenterList;
    }

    public void setInvestmenterList(List<Investmenter> investmenterList) {
        this.investmenterList = investmenterList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
