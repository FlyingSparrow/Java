package com.sparrow.ibe.bookingservice.airbook.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 旅客信息
 *
 * @author wangjc
 * @date 2014-7-11
 */
public class AirTraveler implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4389352619727994605L;
    /**
     * 旅客标识
     */
    private String rph;
    /**
     * 性别，0..1，格式如：M/F
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
     * 出生日期
     */
    private String birthDate;
    private String comment;
    /**
     * 旅客年龄
     */
    private PassengerTypeQuantity passengerTypeQuantity;

    private List<String> flightSegmentRPHList;
    /**
     * 旅客姓名项
     */
    private List<PersonName> personNameList;
    /**
     * 证件信息集合
     */
    private List<Document> documentList;
    /**
     * 旅客序号
     */
    private List<TravelerRefNumber> travelerRefNumberList;
    /**
     * 旅客序号，与航段等信息关联
     */
    private String rphOfTravelerRefNumber;
    private DocumentFlightBinding documentFlightBinding;
    private List<AddressFlightBinding> addressFlightBindingList;
    private List<Address> addressList;

    public String getRph() {
        return rph;
    }

    public void setRph(String rph) {
        this.rph = rph;
    }

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PassengerTypeQuantity getPassengerTypeQuantity() {
        return passengerTypeQuantity;
    }

    public void setPassengerTypeQuantity(PassengerTypeQuantity passengerTypeQuantity) {
        this.passengerTypeQuantity = passengerTypeQuantity;
    }

    public List<String> getFlightSegmentRPHList() {
        return flightSegmentRPHList;
    }

    public void setFlightSegmentRPHList(List<String> flightSegmentRPHList) {
        this.flightSegmentRPHList = flightSegmentRPHList;
    }

    public List<PersonName> getPersonNameList() {
        return personNameList;
    }

    public void setPersonNameList(List<PersonName> personNameList) {
        this.personNameList = personNameList;
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    public List<TravelerRefNumber> getTravelerRefNumberList() {
        return travelerRefNumberList;
    }

    public void setTravelerRefNumberList(List<TravelerRefNumber> travelerRefNumberList) {
        this.travelerRefNumberList = travelerRefNumberList;
    }

    public String getRphOfTravelerRefNumber() {
        return rphOfTravelerRefNumber;
    }

    public void setRphOfTravelerRefNumber(String rphOfTravelerRefNumber) {
        this.rphOfTravelerRefNumber = rphOfTravelerRefNumber;
    }

    public DocumentFlightBinding getDocumentFlightBinding() {
        return documentFlightBinding;
    }

    public void setDocumentFlightBinding(DocumentFlightBinding documentFlightBinding) {
        this.documentFlightBinding = documentFlightBinding;
    }

    public List<AddressFlightBinding> getAddressFlightBindingList() {
        return addressFlightBindingList;
    }

    public void setAddressFlightBindingList(List<AddressFlightBinding> addressFlightBindingList) {
        this.addressFlightBindingList = addressFlightBindingList;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
