package org.smart4j.chapter4.aop;

/**
 * @author wangjianchun
 * @create 2017/12/26
 */
public class GreetingImpl implements Greeting {

    @Override
    public void sayHello(String name) {
        System.out.println("Hello! " + name);
//        throw new RuntimeException("Error");
    }
}
