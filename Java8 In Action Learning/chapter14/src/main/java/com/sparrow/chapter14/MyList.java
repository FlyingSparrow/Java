package com.sparrow.chapter14;

/**
 * @author wangjianchun
 * @create 2018/5/4
 */
public interface MyList<T> {

    T head();

    MyList<T> tail();

    default boolean isEmpty(){
        return true;
    }
}
