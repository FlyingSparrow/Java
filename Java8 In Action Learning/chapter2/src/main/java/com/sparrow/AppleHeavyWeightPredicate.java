package com.sparrow;

/**
 * @author wangjianchun
 * @create 2018/2/10
 */
public class AppleHeavyWeightPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
