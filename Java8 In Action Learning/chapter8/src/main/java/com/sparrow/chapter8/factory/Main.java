package com.sparrow.chapter8.factory;

/**
 * @author wangjianchun
 * @create 2018/4/13
 */
public class Main {

    public static void main(String[] args) {
        test();
        testWithLambda();
    }

    private static void test(){
        Product loan = ProductFactory.createProduct("loan");
    }

    private static void testWithLambda(){
        Product product = ProductFactoryWithJava8.createProduct("loan");
    }

}
