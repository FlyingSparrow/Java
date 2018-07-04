package com.sparrow.ibe.bookingservice.airbook.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 证件信息请求信息
 *
 * @author gengbl
 * @date 2014-10-09
 */
public class Document implements Serializable {

    private static final long serialVersionUID = -7402238396559458136L;
    /**
     * 证件类型，PP - 护照；NI - 身份证
     */
    private String docType;
    /**
     * 证件号
     */
    private String docId;
    /**
     * 证件类型描述，在证件类型为PP时，提供具体护照类型，如：F、P、AC等
     */
    private String docTypeDetail;
    /**
     * 注：对于SSR DOCS 项中的证件持有人标识"H"，由于各个航空公司要求不一致，
     * 这里可以使用"DocHolderInd"属性来控制，当该属性值为true时，生成PNR里的
     * SSR DOCS项将带有"H"标识，若属性值为false时，将不会带有"H"标识。当请求
     * 中没有该属性时，此时将按该属性值为true进行处理，即SSR DOCS项会带有"H"标识。
     */
    private String docHolderInd;
    /**
     * 发证国家
     */
    private String docIssueCountry;
    /**
     * 证件持有人国籍
     */
    private String docHolderNationality;
    /**
     * 出生日期
     */
    private String birthDate;
    /**
     * 证件持有人性别，证件类型为PP时，需要指定性别
     */
    private String gender;
    /**
     * 证件有效期截止日期，证件类型为PP时，需要指定到期时间
     */
    private String expireDate;
    /**
     * 证件编号，若多个证件编号，输入不同编号，都写入主机
     */
    private String rph;
    /**
     * 证件持有人姓名的名，若填写护照，需要填写此项，姓名为zhang/san时，这里是"san"
     */
    private String docHolderGivenName;
    /**
     * 证件持有人姓名的姓，若填写护照，需要填写此项，姓名为zhang/san时，这里是"zhang"
     */
    private String docHolderSurname;
    /**
     * 证件持有人中间名
     */
    private String docHolderMiddleName;

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocTypeDetail() {
        return docTypeDetail;
    }

    public void setDocTypeDetail(String docTypeDetail) {
        this.docTypeDetail = docTypeDetail;
    }

    public String getDocHolderInd() {
        return docHolderInd;
    }

    public void setDocHolderInd(String docHolderInd) {
        this.docHolderInd = docHolderInd;
    }

    public String getDocIssueCountry() {
        return docIssueCountry;
    }

    public void setDocIssueCountry(String docIssueCountry) {
        this.docIssueCountry = docIssueCountry;
    }

    public String getDocHolderNationality() {
        return docHolderNationality;
    }

    public void setDocHolderNationality(String docHolderNationality) {
        this.docHolderNationality = docHolderNationality;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getRph() {
        return rph;
    }

    public void setRph(String rph) {
        this.rph = rph;
    }

    public String getDocHolderGivenName() {
        return docHolderGivenName;
    }

    public void setDocHolderGivenName(String docHolderGivenName) {
        this.docHolderGivenName = docHolderGivenName;
    }

    public String getDocHolderSurname() {
        return docHolderSurname;
    }

    public void setDocHolderSurname(String docHolderSurname) {
        this.docHolderSurname = docHolderSurname;
    }

    public String getDocHolderMiddleName() {
        return docHolderMiddleName;
    }

    public void setDocHolderMiddleName(String docHolderMiddleName) {
        this.docHolderMiddleName = docHolderMiddleName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
