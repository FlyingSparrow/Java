package com.sparrow.decorator.beverage.condiment;

import com.sparrow.decorator.beverage.Beverage;

/**
 * 摩卡
 * @author wangjianchun
 * @create 2018/7/24
 */
public class Mocha extends CondimentDecorator {

    private Beverage beverage;

    public Mocha(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+", Mocha";
    }

    @Override
    public double cost() {
        return .20+beverage.cost();
    }
}
