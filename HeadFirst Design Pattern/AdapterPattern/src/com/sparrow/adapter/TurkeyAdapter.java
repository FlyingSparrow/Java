package com.sparrow.adapter;

import com.sparrow.duck.Duck;
import com.sparrow.turkey.Turkey;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class TurkeyAdapter implements Duck {

    private Turkey turkey;

    public TurkeyAdapter(Turkey turkey){
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for(int i=0; i<5; i++){
            turkey.fly();
        }
    }
}
