package com.sparrow.ibe.bookingservice.airbook.model;

import com.sparrow.ibe.model.DefaultError;
import com.sparrow.ibe.model.DefaultWarning;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * PNR预订服务响应结果类
 *
 * @author wangjc
 * @date 2014-7-7
 */
public class AirBookResponse implements Serializable {

    private static final long serialVersionUID = -4903131714783791605L;

    /**
     * 请求id
     */
    private String requestId;
    /**
     * 成功响应结果，0..n，一个AirReservation可对应一个订单号或一个PNR的信息
     */
    private List<AirReservation> airReservationList;

    /**
     * 错误信息集合，0..n
     */
    private List<DefaultError> errorList;

    /**
     * 警告信息集合，0..n
     */
    private List<DefaultWarning> warningList;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<AirReservation> getAirReservationList() {
        return airReservationList;
    }

    public void setAirReservationList(List<AirReservation> airReservationList) {
        this.airReservationList = airReservationList;
    }

    public List<DefaultError> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<DefaultError> errorList) {
        this.errorList = errorList;
    }

    public List<DefaultWarning> getWarningList() {
        return warningList;
    }

    public void setWarningList(List<DefaultWarning> warningList) {
        this.warningList = warningList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
