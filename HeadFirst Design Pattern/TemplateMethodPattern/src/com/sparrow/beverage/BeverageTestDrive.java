package com.sparrow.beverage;

import com.sparrow.beverage.impl.CoffeeWithHook;
import com.sparrow.beverage.impl.TeaWithHook;

/**
 * @author wangjianchun
 * @create 2018/7/27
 */
public class BeverageTestDrive {

    public static void main(String[] args) {
        TeaWithHook teaWithHook = new TeaWithHook();
        CoffeeWithHook coffeeWithHook = new CoffeeWithHook();

        System.out.println("\nMaking tea...");
        teaWithHook.prepareRecipe();

        System.out.println("\nMaking coffee...");
        coffeeWithHook.prepareRecipe();
    }
}
