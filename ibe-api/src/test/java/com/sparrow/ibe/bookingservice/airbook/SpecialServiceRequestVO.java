package com.sparrow.ibe.bookingservice.airbook;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 特殊服务请求
 * 说明：应该由熟悉 ETERN 订票系统指令的专业人员指导填写该类中的信息
 *
 * @author wangjianchun
 * @create 2018/7/9
 */
public class SpecialServiceRequestVO {

    /**
     * 特殊服务请求代码
     */
    private String ssrCode;
    /**
     * 状态
     */
    private String status;
    /**
     * 航空公司两字码
     */
    private String airline;
    /**
     * FlightRefNumber 的 rph
     */
    private String flightRefNumber;
    /**
     * TravelerRefNumber 的 rph
     */
    private String travelerRefNumber;
    /**
     * 特殊服务请求说明
     */
    private String text;

    public String getSsrCode() {
        return ssrCode;
    }

    public void setSsrCode(String ssrCode) {
        this.ssrCode = ssrCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlightRefNumber() {
        return flightRefNumber;
    }

    public void setFlightRefNumber(String flightRefNumber) {
        this.flightRefNumber = flightRefNumber;
    }

    public String getTravelerRefNumber() {
        return travelerRefNumber;
    }

    public void setTravelerRefNumber(String travelerRefNumber) {
        this.travelerRefNumber = travelerRefNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
