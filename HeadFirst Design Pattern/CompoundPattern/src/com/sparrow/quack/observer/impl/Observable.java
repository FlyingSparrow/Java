package com.sparrow.quack.observer.impl;

import com.sparrow.quack.observer.Observer;
import com.sparrow.quack.observer.QuackObservable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author wangjianchun
 * @create 2018/8/7
 */
public class Observable implements QuackObservable {

    private QuackObservable duck;
    private List<Observer> observers = new ArrayList<>();

    public Observable(QuackObservable duck) {
        this.duck = duck;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        Iterator<Observer> iterator = observers.iterator();
        while (iterator.hasNext()){
            Observer observer = iterator.next();
            observer.update(duck);
        }
    }
}
