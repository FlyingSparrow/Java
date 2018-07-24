package com.sparrow.decorator.beverage.condiment;

import com.sparrow.decorator.beverage.Beverage;

/**
 * 豆浆
 * @author wangjianchun
 * @create 2018/7/24
 */
public class Soy extends CondimentDecorator {

    private Beverage beverage;

    public Soy(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+", Soy";
    }

    @Override
    public double cost() {
        double cost = .15+beverage.cost();
        if(getSize() == Beverage.TALL){
            cost += .10;
        }else if(getSize() == Beverage.GRANDE){
            cost += .15;
        }else if(getSize() == Beverage.VENTI){
            cost += .20;
        }

        return cost;
    }

    @Override
    public int getSize() {
        return beverage.getSize();
    }
}
