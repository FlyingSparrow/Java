package com.sparrow.duck;

/**
 * @author wangjianchun
 * @create 2018/7/27
 */
public class Duck implements Comparable {

    private String name;
    private int weight;

    public Duck(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return name+" weights "+weight;
    }

    @Override
    public int compareTo(Object o) {
        Duck otherDuck = (Duck) o;
        if(this.weight < otherDuck.weight){
            return -1;
        }else if(this.weight == otherDuck.weight){
            return 0;
        }else{
            return 1;
        }
    }
}
