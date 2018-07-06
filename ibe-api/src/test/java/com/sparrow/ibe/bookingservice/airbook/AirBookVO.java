package com.sparrow.ibe.bookingservice.airbook;

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
     * 起飞日期/出发日期时间，格式如：2013-05-29T09:00:00
     */
    private String departureDateTime;
    /**
     * 到达日期时间，格式如：2013-05-29T09:00:00
     */
    private String arrivalDateTime;
    /**
     * 市场方航班号，格式如：1831
     * 市场方航班号(含后缀)，例如：2887A
     * 航司两字码+航班号，格式如：CA1831
     * PNR计算需求。准确计算运价必须输入航班号。如0144
     */
    private String flightNumber;
    /**
     * 出发地(城市)/出发机场三字码，例如：PEK
     */
    private String departureAirport;
    /**
     * 到达地(城市)/到达机场三字码，例如：SHA
     */
    private String arrivalAirport;
    /**
     * 市场方航空公司，航空公司两字码
     */
    private String marketingAirline;
    /**
     * 预订舱位代码/订座代码：预定代号如11
     * 说明：舱位代码（F：头等舱；C：公务舱；Y：经济舱，默认为Y，也就是经济舱）
     */
    private String resBookDesigCode;
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

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getMarketingAirline() {
        return marketingAirline;
    }

    public void setMarketingAirline(String marketingAirline) {
        this.marketingAirline = marketingAirline;
    }

    public String getResBookDesigCode() {
        return resBookDesigCode;
    }

    public void setResBookDesigCode(String resBookDesigCode) {
        this.resBookDesigCode = resBookDesigCode;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
