package com.sparrow.quack.observer.impl;

import com.sparrow.quack.observer.Observer;
import com.sparrow.quack.observer.QuackObservable;

/**
 * @author wangjianchun
 * @create 2018/8/7
 */
public class Quackologist implements Observer {

    @Override
    public void update(QuackObservable duck) {
        System.out.println("Quackologist: "+duck+" just quacked.");
    }
}
