package com.sparrow.chapter9;

/**
 * @author wangjianchun
 * @create 2018/4/20
 */
public class C implements B, A {

    public static void main(String[] args) {
        new C().hello();
        System.out.println(new C().getNumber());
    }

    @Override
    public void hello() {
        A.super.hello();
    }

    @Override
    public Integer getNumber() {
        return B.super.getNumber();
    }
}
