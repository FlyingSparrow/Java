package com.sparrow.ibe.bookingservice.airbookmodify.vo;

import com.sparrow.ibe.bookingservice.airbookmodify.model.AbmAirReservation;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 修改后的预定信息
 *
 * @author wangjianchun
 * @create 2018/7/18
 */
public class AbmRequestVO {

    /**
     * 修改类型
     */
    private String modificationType;
    private String modificationInfo;
    private AbmAirReservation abmAirReservation;

    public String getModificationType() {
        return modificationType;
    }

    public void setModificationType(String modificationType) {
        this.modificationType = modificationType;
    }

    public String getModificationInfo() {
        return modificationInfo;
    }

    public void setModificationInfo(String modificationInfo) {
        this.modificationInfo = modificationInfo;
    }

    public AbmAirReservation getAbmAirReservation() {
        return abmAirReservation;
    }

    public void setAbmAirReservation(AbmAirReservation abmAirReservation) {
        this.abmAirReservation = abmAirReservation;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
