package com.sparrow.ibe.bookingservice.airbook.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 特殊服务请求信息
 *
 * @author wangjc
 * @date 2014-7-7
 */
public class SpecialServiceRequest implements Serializable {

    private static final long serialVersionUID = -8409029532937903784L;

    /**
     * Ssr 类型,服务代码类别，例如FQTV
     */
    private String ssrCode;
    /**
     * 行动代码，例如：HK
     */
    private String status;
    /**
     * 航空公司两字码，特殊服务需要指定航空公司,例如:CA
     */
    private String airlineCode;
    /**
     * 备注信息
     */
    private String text;
    /**
     * 特殊备注所在航段，FQTV必须填写rph，否则不能添加到主机
     */
    private String flightRefNumberRPH;
    /**
     * 旅客引用序号，必须填写rph，否则不能添加到主机
     */
    private String travelerRefNumberRPH;

    private String serviceQuantity;
    /**
     * 特殊服务信息在PNR中的行号
     */
    private String rph;

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

    public String getServiceQuantity() {
        return serviceQuantity;
    }

    public void setServiceQuantity(String serviceQuantity) {
        this.serviceQuantity = serviceQuantity;
    }

    public String getRph() {
        return rph;
    }

    public void setRph(String rph) {
        this.rph = rph;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
