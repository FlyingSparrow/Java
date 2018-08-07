package com.sparrow.quack.factory.impl;

import com.sparrow.quack.Quackable;
import com.sparrow.quack.factory.AbstractDuckFactory;
import com.sparrow.quack.impl.DuckCall;
import com.sparrow.quack.impl.MallardDuck;
import com.sparrow.quack.impl.RedheadDuck;
import com.sparrow.quack.impl.RubberDuck;

/**
 * @author wangjianchun
 * @create 2018/8/7
 */
public class DuckFactory extends AbstractDuckFactory {

    @Override
    public Quackable createMallardDuck() {
        return new MallardDuck();
    }

    @Override
    public Quackable createRedheadDuck() {
        return new RedheadDuck();
    }

    @Override
    public Quackable createDuckCall() {
        return new DuckCall();
    }

    @Override
    public Quackable createRubberDuck() {
        return new RubberDuck();
    }
}
