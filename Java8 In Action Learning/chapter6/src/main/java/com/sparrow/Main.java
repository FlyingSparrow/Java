package com.sparrow;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * @author wangjianchun
 * @create 2018/2/24
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
        collect();
    }

    private static void collect(){
        Comparator<Dish> dishCaloriesComparator = comparingInt(Dish::getCalories);
        Optional<Dish> mostCaloriesDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));

        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("menuStatistics: "+menuStatistics);

        String shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println("shortMenu: "+shortMenu);

        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println("dishesByType: "+dishesByType);

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(groupingBy(dish ->{
            if(dish.getCalories() <= 400){
                return CaloricLevel.DIET;
            }else if(dish.getCalories() <= 700){
                return CaloricLevel.NORMAL;
            }else {
                return CaloricLevel.FAT;
            }
        }));
        System.out.println("dishesByCaloricLevel: "+dishesByCaloricLevel);

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
                .collect(groupingBy(Dish::getType, groupingBy(dish ->{
                    if(dish.getCalories() <= 400){
                        return CaloricLevel.DIET;
                    }else if(dish.getCalories() <= 700){
                        return CaloricLevel.NORMAL;
                    }else {
                        return CaloricLevel.FAT;
                    }
                })));
        System.out.println("dishesByTypeCaloricLevel: "+dishesByTypeCaloricLevel);

        Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println("typesCount: "+typesCount);

        Map<Dish.Type, Dish> mostCaloricByType = menu.stream()
                .collect(groupingBy(Dish::getType, collectingAndThen(
                        maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("mostCaloricByType: "+mostCaloricByType);

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream().collect(
                groupingBy(Dish::getType, mapping(dish -> {
                    if(dish.getCalories() <= 400){
                        return CaloricLevel.DIET;
                    }else if(dish.getCalories() <= 700){
                        return CaloricLevel.NORMAL;
                    }else {
                        return CaloricLevel.FAT;
                    }
                },toCollection(HashSet::new))));
        System.out.println("caloricLevelsByType: "+caloricLevelsByType);

        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println("partitionedMenu: "+partitionedMenu);

        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        System.out.println("vegetarianDishes: "+vegetarianDishes);

        List<Dish> dishes = menu.stream().collect(new ToListCollector<Dish>());
        System.out.println("dishes: "+dishes);
    }

    public enum CaloricLevel {
        DIET, NORMAL, FAT
    }

}
