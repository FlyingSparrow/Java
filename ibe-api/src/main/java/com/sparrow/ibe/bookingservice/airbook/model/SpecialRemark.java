package com.sparrow.ibe.bookingservice.airbook.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 特殊备注信息
 *
 * @author wangjc
 * @date 2014-7-7
 */
public class SpecialRemark implements Serializable {

    private static final long serialVersionUID = 1793910567290787629L;

    /**
     * 航空公司两字码，特殊服务需要指定航空公司
     */
    private String airlineCode;
    /**
     * 备注信息/RMK 项修改需要输入此项
     */
    private String text;
    /**
     * 特殊备注所在航段编号
     */
    private String flightRefNumberRPH;
    /**
     * 指定旅客引用序号，必须填写rph，否则不能添加到主机
     */
    private String travelerRefNumberRPH;

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFlightRefNumberRPH() {
        return flightRefNumberRPH;
    }

    public void setFlightRefNumberRPH(String flightRefNumberRPH) {
        this.flightRefNumberRPH = flightRefNumberRPH;
    }

    public String getTravelerRefNumberRPH() {
        return travelerRefNumberRPH;
    }

    public void setTravelerRefNumberRPH(String travelerRefNumberRPH) {
        this.travelerRefNumberRPH = travelerRefNumberRPH;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
