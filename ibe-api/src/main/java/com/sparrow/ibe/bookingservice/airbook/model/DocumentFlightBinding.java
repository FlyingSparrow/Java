package com.sparrow.ibe.bookingservice.airbook.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 护照航段等信息与旅客的关联关系
 *
 * @author wangjc
 * @date 2014-7-11
 */
public class DocumentFlightBinding implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7802599680524260605L;
    /**
     * 护照序号
     */
    private String documentRPH;
    /**
     * 航段序号
     */
    private String flightSegmentRPH;

    public String getDocumentRPH() {
        return documentRPH;
    }

    public void setDocumentRPH(String documentRPH) {
        this.documentRPH = documentRPH;
    }

    public String getFlightSegmentRPH() {
        return flightSegmentRPH;
    }

    public void setFlightSegmentRPH(String flightSegmentRPH) {
        this.flightSegmentRPH = flightSegmentRPH;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
