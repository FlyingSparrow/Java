package com.sparrow.quack.factory;

import com.sparrow.Goose;
import com.sparrow.quack.Quackable;

/**
 * @author wangjianchun
 * @create 2018/8/7
 */
public abstract class GooseDuckFactory {

    public abstract Quackable createDuck(Goose goose);
}
