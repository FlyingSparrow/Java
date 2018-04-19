package com.sparrow.chapter8.observer;

/**
 * @author wangjianchun
 * @create 2018/4/19
 */
public interface Subject {

    void registerObserver(Observer observer);
    void notifyObservers(String tween);
}
