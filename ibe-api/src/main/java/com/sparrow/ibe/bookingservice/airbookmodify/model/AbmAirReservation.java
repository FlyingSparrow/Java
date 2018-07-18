package com.sparrow.ibe.bookingservice.airbookmodify.model;

import com.sparrow.ibe.bookingservice.airbook.model.BookingReferenceID;
import com.sparrow.ibe.bookingservice.airbook.model.OriginDestinationOption;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 需要修改的信息
 *
 * @author wangjc
 * @date 2014-7-17
 */
public class AbmAirReservation implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1568638730197294211L;

    /**
     * PNR号和订单号，0..1，显示订单号或PNR号信息
     */
    private List<BookingReferenceID> bookingReferenceIDList;

    /**
     * 备注信息集合，0..n
     */
    private List<String> commentList;

    /**
     * 旅客信息
     */
    private List<TravelerInfo> travelerInfoList;
    /**
     * TC项
     */
    private String tourCode;

    private List<OriginDestinationOption> originDestinationList;

    private String endorsement;
    private String exchangeInfo;

    public List<BookingReferenceID> getBookingReferenceIDList() {
        return bookingReferenceIDList;
    }

    public void setBookingReferenceIDList(
            List<BookingReferenceID> bookingReferenceIDList) {
        this.bookingReferenceIDList = bookingReferenceIDList;
    }

    public List<String> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<String> commentList) {
        this.commentList = commentList;
    }

    public List<TravelerInfo> getTravelerInfoList() {
        return travelerInfoList;
    }

    public void setTravelerInfoList(List<TravelerInfo> travelerInfoList) {
        this.travelerInfoList = travelerInfoList;
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

    public void setOriginDestinationList(
            List<OriginDestinationOption> originDestinationList) {
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
