package com.sparrow.quack.factory.impl;

import com.sparrow.quack.Quackable;
import com.sparrow.quack.decorator.QuackCounter;
import com.sparrow.quack.factory.AbstractDuckFactory;
import com.sparrow.quack.impl.DuckCall;
import com.sparrow.quack.impl.MallardDuck;
import com.sparrow.quack.impl.RedheadDuck;
import com.sparrow.quack.impl.RubberDuck;

/**
 * @author wangjianchun
 * @create 2018/8/7
 */
public class CountingDuckFactory extends AbstractDuckFactory {

    @Override
    public Quackable createMallardDuck() {
        return new QuackCounter(new MallardDuck());
    }

    @Override
    public Quackable createRedheadDuck() {
        return new QuackCounter(new RedheadDuck());
    }

    @Override
    public Quackable createDuckCall() {
        return new QuackCounter(new DuckCall());
    }

    @Override
    public Quackable createRubberDuck() {
        return new QuackCounter(new RubberDuck());
    }
}
