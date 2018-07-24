package com.sparrow.decorator.beverage;

/**
 * 深焙咖啡
 * @author wangjianchun
 * @create 2018/7/24
 */
public class DarkRoast extends Beverage {

    public DarkRoast(){
        description = "Dark Roast Coffee";
    }

    @Override
    public double cost() {
        return .99;
    }
}
