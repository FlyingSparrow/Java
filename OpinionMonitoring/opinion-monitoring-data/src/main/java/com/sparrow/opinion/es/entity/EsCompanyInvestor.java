package com.sparrow.opinion.es.entity;

import com.sparrow.opinion.constants.SysConst;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = SysConst.COMPANY_INVESTOR_INDEX, type = SysConst.COMPANY_INVESTOR_TYPE)
public class EsCompanyInvestor implements Serializable {

    private static final long serialVersionUID = 9201034849892179274L;

    @Id
    private String id;

    private String name_c;  //公司名称


    private String property1;   //统一社会信用代码


    private Short investor_type;   //股东类型，1代表自然人，2代表非自然人


    private String name;    //股东名称


    private String del_flag; //"新增ADD 修改EDIT，删除DEL

    public EsCompanyInvestor() {
    }

    public EsCompanyInvestor(String name_c, String property1, Short investor_type, String name, String del_flag) {
        this.name_c = name_c;
        this.property1 = property1;
        this.investor_type = investor_type;
        this.name = name;
        this.del_flag = del_flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_c() {
        return name_c;
    }

    public void setName_c(String name_c) {
        this.name_c = name_c;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public Short getInvestor_type() {
        return investor_type;
    }

    public void setInvestor_type(Short investor_type) {
        this.investor_type = investor_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
