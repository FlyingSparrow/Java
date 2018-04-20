package com.sparrow.chapter10;

import java.util.Optional;

/**
 * @author wangjianchun
 * @create 2018/4/20
 */
public class Person {

    private String name;
    private int age;
    private Optional<Car> car;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Optional<Car> getCar() {
        return car;
    }
}
