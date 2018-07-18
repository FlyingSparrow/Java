package com.sparrow.ibe.bookingservice.airbookmodify.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 接口类别：预订服务
 * 接口名称：自动修改服务
 * 接口ID：JP012
 * 自动修改服务的请求model
 *
 * 说明：该类中的字段是用户在使用自动修改服务时必须填写的信息
 * @author wangjianchun
 * @create 2018/7/16
 */
public class AirBookModifyRequestVO {

    /**
     * 修改后的预定信息
     */
    private AbmRequestVO abmRequestVO;
    /**
     * 修改前的预定信息
     */
    private AirReservationVO airReservationVO;

    public AbmRequestVO getAbmRequestVO() {
        return abmRequestVO;
    }

    public void setAbmRequestVO(AbmRequestVO abmRequestVO) {
        this.abmRequestVO = abmRequestVO;
    }

    public AirReservationVO getAirReservationVO() {
        return airReservationVO;
    }

    public void setAirReservationVO(AirReservationVO airReservationVO) {
        this.airReservationVO = airReservationVO;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
