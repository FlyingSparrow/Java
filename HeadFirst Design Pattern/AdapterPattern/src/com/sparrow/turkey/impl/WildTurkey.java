package com.sparrow.turkey.impl;

import com.sparrow.turkey.Turkey;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class WildTurkey implements Turkey {

    @Override
    public void gobble() {
        System.out.println("Gobble gobble");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying a short distance");
    }
}
