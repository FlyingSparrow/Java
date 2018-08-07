package com.sparrow.gumballmachine.state;

import java.io.Serializable;

/**
 * @author wangjianchun
 * @create 2018/7/31
 */
public interface State extends Serializable {

    void insertQuarter();

    void ejectQuarter();

    boolean turnCrank();

    void dispense();
}
