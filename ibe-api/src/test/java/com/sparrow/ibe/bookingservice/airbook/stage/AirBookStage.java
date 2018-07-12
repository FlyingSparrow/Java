package com.sparrow.ibe.bookingservice.airbook.stage;

import com.sparrow.ibe.bookingservice.airbook.model.AirBookRequest;

/**
 * 接口类别：预订服务
 * 接口名称：自动预订服务
 * 接口ID：JP011
 * 自动预订服务的场景接口，用于根据不同的场景构造自动预定服务的请求对象
 *
 * @author wangjianchun
 * @create 2018/7/11
 */
public interface AirBookStage {

    AirBookRequest buildRequest();

}
