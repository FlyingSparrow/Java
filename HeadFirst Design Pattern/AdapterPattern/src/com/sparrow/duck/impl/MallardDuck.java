package com.sparrow.duck.impl;

import com.sparrow.duck.Duck;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class MallardDuck implements Duck {

    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying");
    }
}
