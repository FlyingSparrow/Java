package com.sparrow;

import java.util.Comparator;

/**
 * @author wangjianchun
 * @create 2018/2/11
 */
public class AppleComparator implements Comparator<Apple> {

    @Override
    public int compare(Apple o1, Apple o2) {
        return o1.getWeight().compareTo(o2.getWeight());
    }
}
