package com.sparrow;

/**
 * @author wangjianchun
 * @create 2018/2/10
 */
public class Apple {

    private String color;
    private Integer weight;
    private String country;

    public Apple(String color, Integer weight){
        this.color = color;
        this.weight = weight;
    }

    public Apple(Integer weight){
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Apple: [color: "+getColor()+", weight: "+getWeight()+", country: "+getCountry()+"]";
    }
}
