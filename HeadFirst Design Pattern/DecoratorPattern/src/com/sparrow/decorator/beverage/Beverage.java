package com.sparrow.decorator.beverage;

/**
 * 饮料
 * @author wangjianchun
 * @create 2018/7/24
 */
public abstract class Beverage {

    /**
     * 小杯
     */
    public static final int TALL = 1;
    /**
     * 中杯
     */
    public static final int GRANDE = 2;
    /**
     * 大杯
     */
    public static final int VENTI = 3;

    protected String description = "Unknown Beverage";
    protected int size = GRANDE;

    public String getDescription() {
        return description;
    }

    public abstract double cost();

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
