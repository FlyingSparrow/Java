package com.sparrow.ibe.bookingservice.airbook;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 旅客信息
 * @author wangjianchun
 * @create 2018/7/6
 */
public class AirTravelerVO {

    /**
     * 性别，格式：MALE：男；FEMALE：女
     */
    private String gender;
    /**
     * 旅客类型，成人 - ADT；婴儿 - INF；儿童 - CHD；JC -
     * 因公带伤警察；GM - 军残；UM - 无人陪伴儿童；OTHER -
     * 其他旅客类型
     */
    private String passengerTypeCode;
    /**
     * 是否有婴儿陪伴，true/false
     */
    private String accompaniedByInfant;
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
     * 证件有效期截止日期，证件类型为PP时，需要指定到期时间
     */
    private String expireDate;
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
    /**
     * 年龄
     */
    private String age;
    /**
     * 旅客所携带的婴儿rph,数字,比如:1,2,3
     */
    private String infantTravelerRph;
    /**
     * 旅客姓名项，当婴儿票预订时，可以输入两组（中文和英文各一组）
     */
    private List<PersonNameVO> personNameList;
    /**
     * 说明信息
     */
    private String comment;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassengerTypeCode() {
        return passengerTypeCode;
    }

    public void setPassengerTypeCode(String passengerTypeCode) {
        this.passengerTypeCode = passengerTypeCode;
    }

    public String getAccompaniedByInfant() {
        return accompaniedByInfant;
    }

    public void setAccompaniedByInfant(String accompaniedByInfant) {
        this.accompaniedByInfant = accompaniedByInfant;
    }

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

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getInfantTravelerRph() {
        return infantTravelerRph;
    }

    public void setInfantTravelerRph(String infantTravelerRph) {
        this.infantTravelerRph = infantTravelerRph;
    }

    public List<PersonNameVO> getPersonNameList() {
        return personNameList;
    }

    public void setPersonNameList(List<PersonNameVO> personNameList) {
        this.personNameList = personNameList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
