package com.sparrow.chapter9;

/**
 * @author wangjianchun
 * @create 2018/4/20
 */
public interface A {

    default void hello(){
        System.out.println("Hello from A");
    }

    default Number getNumber(){
        return 10;
    }
}
