package com.sparrow.quack.adapter;

import com.sparrow.Goose;
import com.sparrow.quack.Quackable;
import com.sparrow.quack.observer.Observer;
import com.sparrow.quack.observer.impl.Observable;

/**
 * @author wangjianchun
 * @create 2018/8/7
 */
public class GooseAdapter implements Quackable {

    private Goose goose;
    private Observable observable;

    public GooseAdapter(Goose goose) {
        this.goose = goose;
        observable = new Observable(this);
    }

    @Override
    public void quack() {
        goose.honk();
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
        return "Goose pretending to be a Duck";
    }
}
