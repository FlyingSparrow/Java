package com.sparrow.chapter8.observer.impl;

import com.sparrow.chapter8.observer.Observer;

/**
 * @author wangjianchun
 * @create 2018/4/19
 */
public class NYTimes implements Observer {

    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("money")){
            System.out.println("Breaking news in NY! "+tweet);
        }
    }
}
