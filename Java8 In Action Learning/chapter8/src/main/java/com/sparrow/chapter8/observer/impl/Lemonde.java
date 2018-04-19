package com.sparrow.chapter8.observer.impl;

import com.sparrow.chapter8.observer.Observer;

/**
 * @author wangjianchun
 * @create 2018/4/19
 */
public class Lemonde implements Observer {

    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("wine")){
            System.out.println("Today cheese, wine and news! "+tweet);
        }
    }
}
