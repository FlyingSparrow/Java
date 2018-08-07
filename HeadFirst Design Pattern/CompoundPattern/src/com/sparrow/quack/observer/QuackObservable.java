package com.sparrow.quack.observer;


/**
 * @author wangjianchun
 * @create 2018/8/7
 */
public interface QuackObservable {

    void registerObserver(Observer observer);
    void notifyObservers();
}
