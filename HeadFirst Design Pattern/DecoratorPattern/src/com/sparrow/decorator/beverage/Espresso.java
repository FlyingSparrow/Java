package com.sparrow.decorator.beverage;

/**
 * 浓缩咖啡
 * @author wangjianchun
 * @create 2018/7/24
 */
public class Espresso extends Beverage {

    public Espresso(){
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
