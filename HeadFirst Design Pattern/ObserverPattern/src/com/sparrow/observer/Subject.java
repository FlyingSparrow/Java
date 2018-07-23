package com.sparrow.observer;

/**
 * @author wangjianchun
 * @create 2018/7/23
 */
public interface Subject {

    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
