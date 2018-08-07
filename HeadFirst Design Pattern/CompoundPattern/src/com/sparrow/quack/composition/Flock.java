package com.sparrow.quack.composition;

import com.sparrow.quack.Quackable;
import com.sparrow.quack.observer.Observer;
import com.sparrow.quack.observer.impl.Observable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author wangjianchun
 * @create 2018/8/7
 */
public class Flock implements Quackable {

    private List<Quackable> quackers = new ArrayList<>();
    private Observable observable;

    public Flock(){
        observable = new Observable(this);
    }

    public void add(Quackable quacker){
        quackers.add(quacker);
    }

    @Override
    public void quack() {
        Iterator<Quackable> iterator = quackers.iterator();
        while (iterator.hasNext()){
            Quackable quacker = iterator.next();
            quacker.quack();
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        Iterator<Quackable> iterator = quackers.iterator();
        while (iterator.hasNext()){
            Quackable duck = iterator.next();
            duck.registerObserver(observer);
        }
    }

    @Override
    public void notifyObservers() {
    }
}
