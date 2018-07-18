package com.sparrow.ibe.bookingservice.airbookmodify.model;

import com.sparrow.ibe.bookingservice.airbook.model.AirReservation;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * 接口类别：预订服务
 * 接口名称：自动修改服务
 * 接口ID：JP012
 * 自动修改服务请求model类
 *
 * @author wangjc
 * @date 2014-7-7
 */
public class AirBookModifyRequest implements Serializable {

    private static final long serialVersionUID = -6130977417258788989L;
    /**
     * 代理人 Office 号
     */
    private String pseudoCityCode;
    /**
     * 修改后的预定信息
     */
    private AirBookModifyRQ airBookModifyRQ;
    /**
     * 修改前的预定信息
     */
    private AirReservation airReservation;

    public String getPseudoCityCode() {
        return pseudoCityCode;
    }

    public void setPseudoCityCode(String pseudoCityCode) {
        this.pseudoCityCode = pseudoCityCode;
    }

    public AirBookModifyRQ getAirBookModifyRQ() {
        return airBookModifyRQ;
    }

    public void setAirBookModifyRQ(AirBookModifyRQ airBookModifyRQ) {
        this.airBookModifyRQ = airBookModifyRQ;
    }

    public AirReservation getAirReservation() {
        return airReservation;
    }

    public void setAirReservation(AirReservation airReservation) {
        this.airReservation = airReservation;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
