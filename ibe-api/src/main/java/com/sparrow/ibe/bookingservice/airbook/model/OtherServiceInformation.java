package com.sparrow.ibe.bookingservice.airbook.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 其他服务信息
 *
 * @author wangjc
 * @date 2014-7-7
 */
public class OtherServiceInformation implements Serializable {

    private static final long serialVersionUID = 8922218748680630696L;

    /**
     * 序号
     */
    private String rph;
    /**
     * OSICode
     */
    private String osiCode = "OTHS";
    /**
     * 航空公司两字码，OSI需要指定航空公司,例如：CA
     */
    private String airlineCode;
    /**
     * 备注信息,OSI 文本/其它信息服务内容
     */
    private String text;
    /**
     * 指定旅客编号
     */
    private String travelerRefNumberRPH;

    public String getRph() {
        return rph;
    }

    public void setRph(String rph) {
        this.rph = rph;
    }

    public String getOsiCode() {
        return osiCode;
    }

    public void setOsiCode(String osiCode) {
        this.osiCode = osiCode;
    }

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
