package com.sparrow.decorator.beverage.condiment;

import com.sparrow.decorator.beverage.Beverage;

/**
 * 奶泡
 * @author wangjianchun
 * @create 2018/7/24
 */
public class Whip extends CondimentDecorator {

    private Beverage beverage;

    public Whip(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+", Whip";
    }

    @Override
    public double cost() {
        return .10+beverage.cost();
    }
}
