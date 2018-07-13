package com.sparrow.ibe.bookingservice.airbook.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 接口类别：预订服务
 * 接口名称：自动预订服务
 * 接口ID：JP011
 * 自动预订服务的响应model
 *
 * @author wangjianchun
 * @create 2018/7/4
 */
public class AirBookResponseVO {

    /**
     * PNR
     */
    private String pnr;
    /**
     * 航段信息列表，1个或多个
     */
    private List<FlightSegmentVO> flightSegmentList;

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public List<FlightSegmentVO> getFlightSegmentList() {
        return flightSegmentList;
    }

    public void setFlightSegmentList(List<FlightSegmentVO> flightSegmentList) {
        this.flightSegmentList = flightSegmentList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
