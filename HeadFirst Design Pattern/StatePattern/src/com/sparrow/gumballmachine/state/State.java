package com.sparrow.gumballmachine.state;

/**
 * @author wangjianchun
 * @create 2018/7/31
 */
public interface State {

    void insertQuarter();

    void ejectQuarter();

    void turnCrank();

    void dispense();
}
