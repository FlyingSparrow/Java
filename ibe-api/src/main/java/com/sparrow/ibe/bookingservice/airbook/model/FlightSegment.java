package com.sparrow.ibe.bookingservice.airbook.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 航段信息：往返或多段可设置多个FlightSegment
 *
 * @author wangjc
 * @date 2014-7-7
 */
public class FlightSegment implements Serializable {

    private static final long serialVersionUID = 8960700383087933406L;

    /**
     * 标识/航段编号，与旅客、SSR等信息关联，多航段时，编号请勿重复/唯一的航段参考代码，
     * 用于交叉涉及额外的航段数据（参见SegmentRPH）。只有当使用航段数据时才是必须的。如1
     */
    private String rph;
    /**
     * 起飞日期/出发日期时间，格式如：2013-05-29T09:00:00
     */
    private String departureDateTime;
    /**
     * 到达日期时间，格式如：2013-05-29T09:00:00
     */
    private String arrivalDateTime;
    /**
     * 是否共享航班，true/false
     */
    private String codeshareInd;
    /**
     * 市场方航班号，格式如：1831
     * 市场方航班号(含后缀)，例如：2887A
     * 航司两字码+航班号，格式如：CA1831
     * PNR计算需求。准确计算运价必须输入航班号。如0144
     */
    private String flightNumber;
    /**
     * 航段预订旅客数(预留)/航段包括人数
     */
    private String numberInParty;
    /**
     * 航段行动代码，即航段状态，如：HK/航班状态：提供航段的状态信息，如26
     */
    private String status;
    /**
     * 航段类别，NORMAL - 普通航段；OPEN -
     * 不定期航段；ARRIVAL_UNKOWN_ARNK - 信息航段。
     * 一般只写NORMAL
     */
    private String segmentType;
    /**
     * 承运方航空公司(预留)，航空公司两字码，例如：MU/共享航班航空公司信息，如：CA
     */
    private String operatingAirline;
    /**
     * 承运方航班号(含后缀)例如：239/共享航班航班号信息，如：1831
     */
    private String flightNumberOfOperatingAirline;
    /**
     * 出发地(城市)/出发机场三字码，例如：PEK
     */
    private String departureAirport;
    /**
     * 到达地(城市)/到达机场三字码，例如：SHA
     */
    private String arrivalAirport;
    /**
     * 机型，例如：773(填写真实机型，机型不同会影响运价结果)
     */
    private String airEquipType;
    /**
     * 市场方航空公司，航空公司两字码
     */
    private String marketingAirline;
    /**
     * 市场方航班号
     */
    private String flightNumberOfMarketingAirline;
    /**
     * 预订舱位代码/订座代码：预定代号如11
     */
    private String resBookDesigCode;

    public String getRph() {
        return rph;
    }

    public void setRph(String rph) {
        this.rph = rph;
    }

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

    public String getCodeshareInd() {
        return codeshareInd;
    }

    public void setCodeshareInd(String codeshareInd) {
        this.codeshareInd = codeshareInd;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getNumberInParty() {
        return numberInParty;
    }

    public void setNumberInParty(String numberInParty) {
        this.numberInParty = numberInParty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSegmentType() {
        return segmentType;
    }

    public void setSegmentType(String segmentType) {
        this.segmentType = segmentType;
    }

    public String getOperatingAirline() {
        return operatingAirline;
    }

    public void setOperatingAirline(String operatingAirline) {
        this.operatingAirline = operatingAirline;
    }

    public String getFlightNumberOfOperatingAirline() {
        return flightNumberOfOperatingAirline;
    }

    public void setFlightNumberOfOperatingAirline(String flightNumberOfOperatingAirline) {
        this.flightNumberOfOperatingAirline = flightNumberOfOperatingAirline;
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

    public String getAirEquipType() {
        return airEquipType;
    }

    public void setAirEquipType(String airEquipType) {
        this.airEquipType = airEquipType;
    }

    public String getMarketingAirline() {
        return marketingAirline;
    }

    public void setMarketingAirline(String marketingAirline) {
        this.marketingAirline = marketingAirline;
    }

    public String getFlightNumberOfMarketingAirline() {
        return flightNumberOfMarketingAirline;
    }

    public void setFlightNumberOfMarketingAirline(String flightNumberOfMarketingAirline) {
        this.flightNumberOfMarketingAirline = flightNumberOfMarketingAirline;
    }

    public String getResBookDesigCode() {
        return resBookDesigCode;
    }

    public void setResBookDesigCode(String resBookDesigCode) {
        this.resBookDesigCode = resBookDesigCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
