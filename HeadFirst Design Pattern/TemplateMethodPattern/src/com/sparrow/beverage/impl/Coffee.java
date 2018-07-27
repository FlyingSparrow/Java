package com.sparrow.beverage.impl;

import com.sparrow.beverage.CaffeineBeverage;

/**
 * @author wangjianchun
 * @create 2018/7/26
 */
public class Coffee extends CaffeineBeverage {

    @Override
    public void brew(){
        System.out.println("Dripping Coffee through filter");
    }

    @Override
    public void addCondiments(){
        System.out.println("Adding Sugar and Milk");
    }

}
