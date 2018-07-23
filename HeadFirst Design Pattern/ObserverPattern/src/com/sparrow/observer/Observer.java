package com.sparrow.observer;

/**
 * @author wangjianchun
 * @create 2018/7/23
 */
public interface Observer {

    void update(float temperature, float humidity, float pressure);
}
