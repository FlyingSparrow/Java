package com.sparrow.quack.decorator;

import com.sparrow.quack.Quackable;
import com.sparrow.quack.observer.Observer;
import com.sparrow.quack.observer.impl.Observable;

/**
 * @author wangjianchun
 * @create 2018/8/7
 */
public class QuackCounter implements Quackable{

    private static int numberOfQuacks;
    private Quackable duck;
    private Observable observable;

    public QuackCounter(Quackable duck) {
        this.duck = duck;
        observable = new Observable(duck);
    }

    @Override
    public void quack() {
        duck.quack();
        numberOfQuacks++;
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

    public static final int getNumberOfQuacks(){
        return numberOfQuacks;
    }
}
