package com.jdjr.opinion.mongodb.entity;

import com.jdjr.opinion.base.bean.BasePageRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
@Document(collection = "enterprise_info")
public class EnterpriseInfo extends BasePageRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9201034849892179274L;
    @Id
    @Indexed(unique = true)
    private String id;

    @Field("enterprise_name")
    private String enterpriseName;//企业名称（全称）

    @Field("registered_capital")
    private String registeredCapital;//注册资本

    @Field("shareholder")
    private String shareholder;//股东

    @Field("foreign_investment")
    private String foreignInvestment;//对外投资

    @Field("business_scope")
    private String businessScope;//经营范围

    @Field("director_senior_supervisor")
    private String directorSeniorSupervisor;//董高监（董事、高层管理人员、监事）

    @Field("legal_person")
    private String legalPerson;//法人

    @Field("enterprise_abbreviation")
    private String enterpriseAbbreviation;//公司简称

    @Field("investmenter_list")
    private List<Investmenter> investmenterList;//投资人

    @Field("keywords")
    private String keywords;//关键词

    @Field("created_date")
    private Date createdDate; // 创建时间

    @Field("modified_date")
    private Date modifiedDate; // 更新时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<Investmenter> getInvestmenterList() {
        return investmenterList;
    }

    public void setInvestmenterList(List<Investmenter> investmenterList) {
        this.investmenterList = investmenterList;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
