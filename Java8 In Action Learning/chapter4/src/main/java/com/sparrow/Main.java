package com.sparrow;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wangjianchun
 * @create 2018/2/11
 */
public class Main {

    private static final List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    public static void main(String[] args) {
        lowCaloricDishesWithJava7();
        lowCaloricDishesWithJava8();
        threeHighCaloricDishesWithJava8();
    }

    private static void lowCaloricDishesWithJava7(){
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish dish : menu){
            if(dish.getCalories() < 400){
                lowCaloricDishes.add(dish);
            }
        }

        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });

        List<String> lowCaloricDishesName = new ArrayList<>();
        for(Dish dish : lowCaloricDishes){
            lowCaloricDishesName.add(dish.getName());
        }
        System.out.println("lowCaloricDishes with java 7 way");
        System.out.println(lowCaloricDishesName);
    }

    private static void lowCaloricDishesWithJava8(){
        List<String> lowCaloricDishesName = menu.parallelStream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName).collect(Collectors.toList());

        System.out.println("lowCaloricDishes with java 8 way");
        System.out.println(lowCaloricDishesName);
    }

    private static void threeHighCaloricDishesWithJava8(){
        List<String> threeHighCaloricDishesName = menu.parallelStream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName).limit(3).collect(Collectors.toList());

        List<String> names = menu.parallelStream().filter(dish -> {
            System.out.println("filtering "+dish.getName());
            return dish.getCalories() > 300;
        }).map(dish -> {
            System.out.println("mapping "+dish.getName());
            return dish.getName();
        }).limit(3).collect(Collectors.toList());

        System.out.println("threeHighCaloricDishes with java 8 way");
        System.out.println(threeHighCaloricDishesName);
    }

}
