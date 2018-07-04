package com.sparrow.ibe.bookingservice.airbook.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 旅客序号标识信息/旅客参考编号(OTA必须)
 * 请不要输入重复的值
 *
 * @author gengbl
 * @date 2014-10-10
 */
public class TravelerRefNumber implements Serializable {

    private static final long serialVersionUID = 8063584596451591942L;
    /**
     * 编号/旅客rph(旅客序号)/与航段等信息关联,数字,比如:1,2,3
     * <p>
     * 单一的旅客参考编号,用于与新增旅客数据进行交叉联系
     * (见PassengerRPH:旅客指数--旅客指数将新增旅客数据与PassengerTypeQuantity元素和AirTraveler元素相关联如:1)
     */
    private String rph;
    /**
     * 旅客所携带的婴儿rph,数字,比如:1,2,3
     */
    private String infantTravelerRPH;

    public String getRph() {
        return rph;
    }

    public void setRph(String rph) {
        this.rph = rph;
    }

    public String getInfantTravelerRPH() {
        return infantTravelerRPH;
    }

    public void setInfantTravelerRPH(String infantTravelerRPH) {
        this.infantTravelerRPH = infantTravelerRPH;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
