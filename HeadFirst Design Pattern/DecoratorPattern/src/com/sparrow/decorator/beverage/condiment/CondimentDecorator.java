package com.sparrow.decorator.beverage.condiment;

import com.sparrow.decorator.beverage.Beverage;

/**
 * 调料装饰器
 * @author wangjianchun
 * @create 2018/7/24
 */
public abstract class CondimentDecorator extends Beverage {

    @Override
    public abstract String getDescription();
}
