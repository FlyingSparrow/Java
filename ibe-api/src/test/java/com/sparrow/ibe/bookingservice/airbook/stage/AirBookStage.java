package com.sparrow.ibe.bookingservice.airbook.stage;

import com.sparrow.ibe.bookingservice.airbook.model.AirBookRequest;

/**
 *
 * @author wangjianchun
 * @create 2018/7/11
 */
public interface AirBookStage {

    AirBookRequest buildRequest();

}
