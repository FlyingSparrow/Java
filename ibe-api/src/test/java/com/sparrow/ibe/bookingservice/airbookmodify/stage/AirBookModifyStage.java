package com.sparrow.ibe.bookingservice.airbookmodify.stage;

import com.sparrow.ibe.bookingservice.airbookmodify.model.AirBookModifyRequest;

/**
 * 接口类别：预订服务
 * 接口名称：自动修改服务
 * 接口ID：JP012
 * 自动修改服务的场景接口，用于根据不同的场景构造自动预定服务的请求对象
 *
 * @author wangjianchun
 * @create 2018/7/11
 */
public interface AirBookModifyStage {

    AirBookModifyRequest buildRequest();

}
