package com.sparrow.menu.impl;

import com.sparrow.iterator.impl.NullIterator;
import com.sparrow.menu.MenuComponent;

import java.util.Iterator;

/**
 * @author wangjianchun
 * @create 2018/7/27
 */
public class MenuItem extends MenuComponent {

    private String name;
    private String description;
    private boolean vegetarian;
    private double price;

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public boolean isVegetarian() {
        return vegetarian;
    }
    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void print() {
        System.out.print("  "+getName());
        if(isVegetarian()){
            System.out.print("(v)");
        }
        System.out.print(", "+getPrice());
        System.out.println("    -- "+getDescription());
    }

    public Iterator createIterator(){
        return new NullIterator();
    }
}
