package com.sparrow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangjianchun
 * @create 2018/2/10
 */
public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple("green", 80),
                new Apple("green", 155), new Apple("red", 120));

        List<Apple> heavyApples = filterApples(inventory, new AppleHeavyWeightPredicate());
        List<Apple> greenColorApples = filterApples(inventory, new AppleGreenColorPredicate());
        List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });

        List<Apple> results = filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple : inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }

        return result;
    }
}
