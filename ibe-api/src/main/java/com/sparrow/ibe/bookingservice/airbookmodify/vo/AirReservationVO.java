package com.sparrow.ibe.bookingservice.airbookmodify.vo;

import com.sparrow.ibe.bookingservice.airbook.model.FlightSegment;
import com.sparrow.ibe.bookingservice.airbook.model.OriginDestinationOption;
import com.sparrow.ibe.bookingservice.airbookmodify.model.TravelerInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 修改前的预定信息
 *
 * @author wangjianchun
 * @create 2018/7/18
 */
public class AirReservationVO {

    /**
     * 航段信息集合，1..n，往返或多段可以设置多个FlightSegment
     */
    private List<FlightSegment> flightSegmentList;
    /**
     * 旅客姓名集合，例如：英文：zhang/san；中文：张三
     */
    private List<String> travelerNameList;
    /**
     * PNR号
     */
    private String pnr;
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
    private List<TravelerInfo> travelerInfoList;

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

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
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

    public List<TravelerInfo> getTravelerInfoList() {
        return travelerInfoList;
    }

    public void setTravelerInfoList(List<TravelerInfo> travelerInfoList) {
        this.travelerInfoList = travelerInfoList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
