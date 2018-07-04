package com.sparrow.ibe.bookingservice.airbook.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 功能：旅客模块
 *
 * @author sunk
 */
public class PassengerTypeQuantity implements Serializable {

    private static final long serialVersionUID = -5228267327621483689L;
    /**
     * 编号
     * <p>
     * 单一的旅客参考编号，用于与新增旅客数据进行交叉联系.
     * (见PassengerRPH:旅客指数--旅客指数将新增旅客数据
     * 与PassengerTypeQuantity元素和AirTraveler元素相关联,如：1)
     */
    private String rph;
    /**
     * 年龄
     */
    private String age;
    /**
     * 乘客/旅客/用户类型代码，成人 - ADT；儿童 - CHD；婴儿 - INF；无成人陪伴儿童 - UN
     */
    private String code;
    /**
     * 指定旅客类型的数量,如 1
     */
    private String quantity;

    public String getRph() {
        return rph;
    }

    public void setRph(String rph) {
        this.rph = rph;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
