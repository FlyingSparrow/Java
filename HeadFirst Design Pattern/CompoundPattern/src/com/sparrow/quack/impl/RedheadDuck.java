package com.sparrow.quack.impl;

import com.sparrow.quack.Quackable;
import com.sparrow.quack.observer.Observer;
import com.sparrow.quack.observer.impl.Observable;

/**
 * @author wangjianchun
 * @create 2018/8/7
 */
public class RedheadDuck implements Quackable {

    private Observable observable;

    public RedheadDuck(){
        observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("Quack");
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }

    @Override
    public String toString() {
        return "Redhead Duck";
    }
}
