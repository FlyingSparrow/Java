package com.sparrow.opinion.es.entity;

import com.sparrow.opinion.constants.SysConst;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Document(indexName = SysConst.COMPANY_BASE_INDEX, type = SysConst.COMPANY_BASE_TYPE)
public class EsCompanyBase implements Serializable {

    private static final long serialVersionUID = 9201034849892179274L;

    @Id
    private String id;

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String company_name_md5;    //	企业名称md5

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String company_name;    //	企业名称

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String company_name_1;    //	企业曾用名称

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String status;    //	公司是否有效，1有效0无效

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String reg_number;    //	工商注册号

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String property1;    //	统一社会信用代码

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String org_number;    //	组织机构代码

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String legal_person_name;    //	法人

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String reg_capital;    //	注册资金

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String estiblish_time;    //	成立日期

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String from_time;    //	营业期限开始日期

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String to_time;    //	营业期限终止日期

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String reg_status;    //	企业状态

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String business_scope;    //	经营范围

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String categpry_str;    //	行业门类

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String categpry_str2;    //	行业大类

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String categpry_str3;    //	行业中类

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String province;    //	省份

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String phone_number;    //	企业电话

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String staff_name;    //	高管姓名(多个用,号隔开)

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String investor_name;    //	股东名称(多个用,号隔开)

    @Field(index = FieldIndex.not_analyzed, type = FieldType.Double)
    private Double amount;    //	注册金额(统一成人民币万元)

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String approved_time;    //	核准日期

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String email;    //	电子邮箱

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String reg_location;    //	注册地址

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String reg_institute;    //	登记机关

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String website;    //	网址

    @Field(index = FieldIndex.not_analyzed, type = FieldType.String)
    private String company_org_type;    //	公司类型

    public EsCompanyBase() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany_name_md5() {
        return company_name_md5;
    }

    public void setCompany_name_md5(String company_name_md5) {
        this.company_name_md5 = company_name_md5;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_name_1() {
        return company_name_1;
    }

    public void setCompany_name_1(String company_name_1) {
        this.company_name_1 = company_name_1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReg_number() {
        return reg_number;
    }

    public void setReg_number(String reg_number) {
        this.reg_number = reg_number;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getOrg_number() {
        return org_number;
    }

    public void setOrg_number(String org_number) {
        this.org_number = org_number;
    }

    public String getLegal_person_name() {
        return legal_person_name;
    }

    public void setLegal_person_name(String legal_person_name) {
        this.legal_person_name = legal_person_name;
    }

    public String getReg_capital() {
        return reg_capital;
    }

    public void setReg_capital(String reg_capital) {
        this.reg_capital = reg_capital;
    }

    public String getEstiblish_time() {
        return estiblish_time;
    }

    public void setEstiblish_time(String estiblish_time) {
        this.estiblish_time = estiblish_time;
    }

    public String getFrom_time() {
        return from_time;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public String getTo_time() {
        return to_time;
    }

    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }

    public String getReg_status() {
        return reg_status;
    }

    public void setReg_status(String reg_status) {
        this.reg_status = reg_status;
    }

    public String getBusiness_scope() {
        return business_scope;
    }

    public void setBusiness_scope(String business_scope) {
        this.business_scope = business_scope;
    }

    public String getCategpry_str() {
        return categpry_str;
    }

    public void setCategpry_str(String categpry_str) {
        this.categpry_str = categpry_str;
    }

    public String getCategpry_str2() {
        return categpry_str2;
    }

    public void setCategpry_str2(String categpry_str2) {
        this.categpry_str2 = categpry_str2;
    }

    public String getCategpry_str3() {
        return categpry_str3;
    }

    public void setCategpry_str3(String categpry_str3) {
        this.categpry_str3 = categpry_str3;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getInvestor_name() {
        return investor_name;
    }

    public void setInvestor_name(String investor_name) {
        this.investor_name = investor_name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getApproved_time() {
        return approved_time;
    }

    public void setApproved_time(String approved_time) {
        this.approved_time = approved_time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReg_location() {
        return reg_location;
    }

    public void setReg_location(String reg_location) {
        this.reg_location = reg_location;
    }

    public String getReg_institute() {
        return reg_institute;
    }

    public void setReg_institute(String reg_institute) {
        this.reg_institute = reg_institute;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompany_org_type() {
        return company_org_type;
    }

    public void setCompany_org_type(String company_org_type) {
        this.company_org_type = company_org_type;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}
