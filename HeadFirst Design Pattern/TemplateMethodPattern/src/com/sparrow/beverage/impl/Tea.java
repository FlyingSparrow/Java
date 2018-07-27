package com.sparrow.beverage.impl;

import com.sparrow.beverage.CaffeineBeverage;

/**
 * @author wangjianchun
 * @create 2018/7/26
 */
public class Tea extends CaffeineBeverage {

    @Override
    public void brew(){
        System.out.println("Steeping the tea");
    }

    @Override
    public void addCondiments(){
        System.out.println("Adding Lemon");
    }

}
