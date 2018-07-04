package com.sparrow.ibe.bookingservice.airbook.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 *
 * @author wangjc
 * @date 2014-7-11
 */
public class AddressFlightBinding implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2350013921214581072L;

    private String addressRPH;
    private String flightSegmentRPH;

    public String getAddressRPH() {
        return addressRPH;
    }

    public void setAddressRPH(String addressRPH) {
        this.addressRPH = addressRPH;
    }

    public String getFlightSegmentRPH() {
        return flightSegmentRPH;
    }

    public void setFlightSegmentRPH(String flightSegmentRPH) {
        this.flightSegmentRPH = flightSegmentRPH;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
