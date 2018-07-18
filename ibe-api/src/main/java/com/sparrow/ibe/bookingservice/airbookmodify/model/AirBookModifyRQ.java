package com.sparrow.ibe.bookingservice.airbookmodify.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

public class AirBookModifyRQ implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6371931370617669709L;
    /**
     * 修改类型
     */
    private String modificationType;
    private String modificationInfo;
    private List<AbmAirReservation> abmAirReservationList;

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

    public List<AbmAirReservation> getAbmAirReservationList() {
        return abmAirReservationList;
    }

    public void setAbmAirReservationList(
            List<AbmAirReservation> abmAirReservationList) {
        this.abmAirReservationList = abmAirReservationList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
