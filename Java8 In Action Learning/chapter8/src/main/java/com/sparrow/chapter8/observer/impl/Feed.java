package com.sparrow.chapter8.observer.impl;

import com.sparrow.chapter8.observer.Observer;
import com.sparrow.chapter8.observer.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjianchun
 * @create 2018/4/19
 */
public class Feed implements Subject {

    private final List<Observer> observerList = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void notifyObservers(String tween) {
        observerList.forEach(observer -> observer.notify(tween));
    }
}
