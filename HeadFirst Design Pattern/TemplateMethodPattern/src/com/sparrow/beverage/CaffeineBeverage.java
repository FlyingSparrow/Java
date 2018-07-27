package com.sparrow.beverage;

/**
 * @author wangjianchun
 * @create 2018/7/26
 */
public abstract class CaffeineBeverage {

    public final void prepareRecipe(){
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    protected abstract void brew();

    protected abstract void addCondiments();

    protected void boilWater(){
        System.out.println("Boiling water");
    }

    protected void pourInCup(){
        System.out.println("Pouring into cup");
    }

}
