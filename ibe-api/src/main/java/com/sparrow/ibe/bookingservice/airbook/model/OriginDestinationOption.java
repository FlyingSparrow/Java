package com.sparrow.ibe.bookingservice.airbook.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangjianchun
 * @create 2018/7/3
 */
public class OriginDestinationOption implements Serializable {

    /**
     * 序号
     */
    private String rph;

    /**
     * 航段信息集合，1..n，往返或多段可以设置多个FlightSegment
     */
    private List<FlightSegment> flightSegmentList;

    public String getRph() {
        return rph;
    }

    public void setRph(String rph) {
        this.rph = rph;
    }

    public List<FlightSegment> getFlightSegmentList() {
        return flightSegmentList;
    }

    public void setFlightSegmentList(List<FlightSegment> flightSegmentList) {
        this.flightSegmentList = flightSegmentList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
