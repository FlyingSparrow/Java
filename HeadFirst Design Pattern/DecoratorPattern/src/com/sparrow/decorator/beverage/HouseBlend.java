package com.sparrow.decorator.beverage;

/**
 * 综合咖啡
 * @author wangjianchun
 * @create 2018/7/24
 */
public class HouseBlend extends Beverage {

    public HouseBlend(){
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return .89;
    }
}
