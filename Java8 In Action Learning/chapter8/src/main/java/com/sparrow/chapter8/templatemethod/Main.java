package com.sparrow.chapter8.templatemethod;

/**
 * @author wangjianchun
 * @create 2018/4/13
 */
public class Main {

    public static void main(String[] args) {
        new OnlineBankingLambda().processCustomer(1337, (Customer customer) -> {
            System.out.println("Hello "+customer.getName());
        });
    }

}
