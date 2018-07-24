package com.sparrow.decorator.beverage.condiment;

import com.sparrow.decorator.beverage.Beverage;

/**
 * 牛奶
 * @author wangjianchun
 * @create 2018/7/24
 */
public class Milk extends CondimentDecorator {

    private Beverage beverage;

    public Milk(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+", Milk";
    }

    @Override
    public double cost() {
        return .10+beverage.cost();
    }
}
