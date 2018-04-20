package com.sparrow.chapter9;

/**
 * @author wangjianchun
 * @create 2018/4/20
 */
public interface B {

    default void hello(){
        System.out.println("Hello from B");
    }

    default Integer getNumber(){
        return 42;
    }
}
