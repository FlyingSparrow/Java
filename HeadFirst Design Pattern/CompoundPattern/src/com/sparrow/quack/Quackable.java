package com.sparrow.quack;

import com.sparrow.quack.observer.QuackObservable;

/**
 * @author wangjianchun
 * @create 2018/8/7
 */
public interface Quackable extends QuackObservable {

    void quack();

}
