package com.sparrow;

/**
 * @author wangjianchun
 * @create 2018/2/10
 */
public class AppleGreenColorPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
