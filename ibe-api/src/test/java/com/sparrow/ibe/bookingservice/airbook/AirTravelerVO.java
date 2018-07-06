package com.sparrow.ibe.bookingservice.airbook;

import org.apache.commons.lang3.builder.ToStringBuilder;

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
     * 拼写语言，0..1，姓名的输入语言，中文 - ZH；英文 - EN，
     * 婴儿票输入护照信息前，需要输入英文姓名
     */
    private String languageType;
    /**
     * 旅客姓名，格式如：中文：张三；英文：Zhang/san
     */
    private String surname;
    /**
     * 证件类型，PP - 护照；NI - 身份证
     */
    private String docType;
    /**
     * 证件号
     */
    private String docId;

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

    public String getLanguageType() {
        return languageType;
    }

    public void setLanguageType(String languageType) {
        this.languageType = languageType;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
