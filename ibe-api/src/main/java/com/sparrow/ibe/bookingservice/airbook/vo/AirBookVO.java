package com.sparrow.ibe.bookingservice.airbook.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 接口类别：预订服务
 * 接口名称：自动预订服务
 * 接口ID：JP011
 * 自动预订服务的请求model
 *
 * 说明：该类中的字段是用户在使用自动预定服务时必须填写的信息
 *
 * @author wangjianchun
 * @create 2018/7/4
 */
public class AirBookVO {

    /**
     * 航段信息列表
     */
    private List<FlightSegmentVO> flightSegmentList;
    /**
     * 出票时限，格式如：2015-12-16T00:01:00
     */
    private String ticketTimeLimit;
    /**
     * 联系电话
     */
    private String contactNumber;
    /**
     * 旅客信息列表
     */
    private List<AirTravelerVO> airTravelerList;
    /**
     * OSI（其他服务信息）信息列表
     */
    private List<String> osiList;
    /**
     * 备注信息列表
     */
    private List<String> remarkList;
    /**
     * 特殊服务请求信息列表
     */
    private List<SpecialServiceRequestVO> ssrList;
    /**
     * 封口延迟，0..1，default true，若需一次封口，请设置为false
     */
    private String envelopDelay;

    public List<FlightSegmentVO> getFlightSegmentList() {
        return flightSegmentList;
    }

    public void setFlightSegmentList(List<FlightSegmentVO> flightSegmentList) {
        this.flightSegmentList = flightSegmentList;
    }

    public String getTicketTimeLimit() {
        return ticketTimeLimit;
    }

    public void setTicketTimeLimit(String ticketTimeLimit) {
        this.ticketTimeLimit = ticketTimeLimit;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<AirTravelerVO> getAirTravelerList() {
        return airTravelerList;
    }

    public void setAirTravelerList(List<AirTravelerVO> airTravelerList) {
        this.airTravelerList = airTravelerList;
    }

    public List<String> getOsiList() {
        return osiList;
    }

    public void setOsiList(List<String> osiList) {
        this.osiList = osiList;
    }

    public List<String> getRemarkList() {
        return remarkList;
    }

    public void setRemarkList(List<String> remarkList) {
        this.remarkList = remarkList;
    }

    public List<SpecialServiceRequestVO> getSsrList() {
        return ssrList;
    }

    public void setSsrList(List<SpecialServiceRequestVO> ssrList) {
        this.ssrList = ssrList;
    }

    public String getEnvelopDelay() {
        return envelopDelay;
    }

    public void setEnvelopDelay(String envelopDelay) {
        this.envelopDelay = envelopDelay;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
