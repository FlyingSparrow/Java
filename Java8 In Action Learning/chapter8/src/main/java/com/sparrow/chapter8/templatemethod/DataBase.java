package com.sparrow.chapter8.templatemethod;

/**
 * @author wangjianchun
 * @create 2018/4/13
 */
public class DataBase {

    public static Customer getCustomerWithId(int id) {
        return new Customer(24, "Jerry");
    }
}
