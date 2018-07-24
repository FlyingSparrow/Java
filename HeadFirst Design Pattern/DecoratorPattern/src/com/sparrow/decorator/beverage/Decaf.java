package com.sparrow.decorator.beverage;

/**
 * 低咖啡因咖啡
 * @author wangjianchun
 * @create 2018/7/24
 */
public class Decaf extends Beverage {

    public Decaf(){
        description = "Decaf";
    }

    @Override
    public double cost() {
        return 1.05;
    }
}
