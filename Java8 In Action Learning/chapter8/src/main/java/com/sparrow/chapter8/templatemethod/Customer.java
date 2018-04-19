package com.sparrow.chapter8.templatemethod;

/**
 * @author wangjianchun
 * @create 2018/4/13
 */
public class Customer {

    private int id;
    private String name;

    public Customer(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
