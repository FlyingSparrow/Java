package com.sparrow.ibe.bookingservice.airbook.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 成功响应结果，一个AirReservation可对应一个订单号或一个PNR的信息
 *
 * @author wangjc
 * @date 2014-7-7
 */
public class AirReservation implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1660447976100578437L;

    /**
     * 航段信息集合，1..n，往返或多段可以设置多个FlightSegment
     */
    private List<FlightSegment> flightSegmentList;
    /**
     * 旅客姓名集合，例如：英文：zhang/san；中文：张三
     */
    private List<String> travelerNameList;
    /**
     * PNR号和订单号，0..1，显示订单号或PNR号信息
     */
    private List<BookingReferenceID> bookingReferenceIDList;
    /**
     * 备注信息集合，0..n
     */
    private List<String> commentList;
    /**
     * TC项
     */
    private String tourCode;
    /**
     * 出发到达地信息集合
     */
    private List<OriginDestinationOption> originDestinationList;
    /**
     * 签注信息
     */
    private String endorsement;
    private String exchangeInfo;

    public List<FlightSegment> getFlightSegmentList() {
        return flightSegmentList;
    }

    public void setFlightSegmentList(List<FlightSegment> flightSegmentList) {
        this.flightSegmentList = flightSegmentList;
    }

    public List<String> getTravelerNameList() {
        return travelerNameList;
    }

    public void setTravelerNameList(List<String> travelerNameList) {
        this.travelerNameList = travelerNameList;
    }

    public List<BookingReferenceID> getBookingReferenceIDList() {
        return bookingReferenceIDList;
    }

    public void setBookingReferenceIDList(List<BookingReferenceID> bookingReferenceIDList) {
        this.bookingReferenceIDList = bookingReferenceIDList;
    }

    public List<String> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<String> commentList) {
        this.commentList = commentList;
    }

    public String getTourCode() {
        return tourCode;
    }

    public void setTourCode(String tourCode) {
        this.tourCode = tourCode;
    }

    public List<OriginDestinationOption> getOriginDestinationList() {
        return originDestinationList;
    }

    public void setOriginDestinationList(List<OriginDestinationOption> originDestinationList) {
        this.originDestinationList = originDestinationList;
    }

    public String getEndorsement() {
        return endorsement;
    }

    public void setEndorsement(String endorsement) {
        this.endorsement = endorsement;
    }

    public String getExchangeInfo() {
        return exchangeInfo;
    }

    public void setExchangeInfo(String exchangeInfo) {
        this.exchangeInfo = exchangeInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
