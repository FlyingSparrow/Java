package com.sparrow.chapter8.observer;

import com.sparrow.chapter8.observer.impl.Feed;
import com.sparrow.chapter8.observer.impl.Guardian;
import com.sparrow.chapter8.observer.impl.Lemonde;
import com.sparrow.chapter8.observer.impl.NYTimes;

/**
 * @author wangjianchun
 * @create 2018/4/13
 */
public class Main {

    public static void main(String[] args) {
        observerTest();
        observerWithLambda();
    }

    private static void observerTest(){
        Feed feed = new Feed();
        feed.registerObserver(new NYTimes());
        feed.registerObserver(new Guardian());
        feed.registerObserver(new Lemonde());
        feed.notifyObservers("The queen said her favourite book is Java 8 in Action!");
    }

    private static void observerWithLambda(){
        Feed feed = new Feed();
        feed.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("money")){
                System.out.println("Breaking news in NY! "+tweet);
            }
        });

        feed.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("queen")){
                System.out.println("Yet another new in London... "+tweet);
            }
        });

        feed.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("wine")){
                System.out.println("Today cheese, wine and news! "+tweet);
            }
        });

        feed.notifyObservers("The queen said her favourite book is Java 8 in Action!");
    }

}
