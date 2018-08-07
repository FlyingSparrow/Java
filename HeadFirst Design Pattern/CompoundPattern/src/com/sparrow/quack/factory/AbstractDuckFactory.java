package com.sparrow.quack.factory;

import com.sparrow.quack.Quackable;

/**
 * @author wangjianchun
 * @create 2018/8/7
 */
public abstract class AbstractDuckFactory {

    public abstract Quackable createMallardDuck();
    public abstract Quackable createRedheadDuck();
    public abstract Quackable createDuckCall();
    public abstract Quackable createRubberDuck();

}
