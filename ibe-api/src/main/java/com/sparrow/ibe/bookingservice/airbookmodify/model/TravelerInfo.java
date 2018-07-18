package com.sparrow.ibe.bookingservice.airbookmodify.model;

import com.sparrow.ibe.bookingservice.airbook.model.AirTraveler;
import com.sparrow.ibe.bookingservice.airbook.model.OtherServiceInformation;
import com.sparrow.ibe.bookingservice.airbook.model.SpecialRemark;
import com.sparrow.ibe.bookingservice.airbook.model.SpecialServiceRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 旅客信息
 *
 * @author wangjc
 * @date 2014-7-7
 */
public class TravelerInfo implements Serializable {

    private static final long serialVersionUID = -7291197397843948370L;

    /**
     * 旅客信息
     */
    private List<AirTraveler> airTravelerList;

    /**
     * 特殊备注信息集合
     */
    private List<SpecialRemark> remarkList;

    /**
     * 其他服务信息集合
     */
    private List<OtherServiceInformation> osiList;

    /**
     * 特殊服务请求集合
     */
    private List<SpecialServiceRequest> ssrList;

    /**
     * EI项
     */
    private String exchangeInfo;

    /**
     * C项
     */
    private String tourCode;

    public List<AirTraveler> getAirTravelerList() {
        return airTravelerList;
    }

    public void setAirTravelerList(List<AirTraveler> airTravelerList) {
        this.airTravelerList = airTravelerList;
    }

    public List<SpecialRemark> getRemarkList() {
        return remarkList;
    }

    public void setRemarkList(List<SpecialRemark> remarkList) {
        this.remarkList = remarkList;
    }

    public List<OtherServiceInformation> getOsiList() {
        return osiList;
    }

    public void setOsiList(List<OtherServiceInformation> osiList) {
        this.osiList = osiList;
    }

    public List<SpecialServiceRequest> getSsrList() {
        return ssrList;
    }

    public void setSsrList(List<SpecialServiceRequest> ssrList) {
        this.ssrList = ssrList;
    }

    public String getExchangeInfo() {
        return exchangeInfo;
    }

    public void setExchangeInfo(String exchangeInfo) {
        this.exchangeInfo = exchangeInfo;
    }

    public String getTourCode() {
        return tourCode;
    }

    public void setTourCode(String tourCode) {
        this.tourCode = tourCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
