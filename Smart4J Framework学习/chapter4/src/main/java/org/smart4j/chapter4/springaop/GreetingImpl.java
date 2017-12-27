package org.smart4j.chapter4.springaop;

import org.smart4j.chapter4.aop.Greeting;
import org.springframework.stereotype.Component;

/**
 * @author wangjianchun
 * @create 2017/12/26
 */
//@Component
public class GreetingImpl implements Greeting {

//    @Tag
    @Override
    public void sayHello(String name) {
        System.out.println("Hello! " + name);
//        throw new RuntimeException("Error");
    }

    public void goodMorning(String name){
        System.out.println("Good Morning! "+name);
    }

    public void goodNight(String name){
        System.out.println("Good Night! "+name);
    }
}
