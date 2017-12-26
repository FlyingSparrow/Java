package org.smart4j.chapter4;

import org.smart4j.chapter4.cglibproxy.CGLibDynamicProxy;
import org.smart4j.chapter4.dynamicproxy.JDKDynamicProxy;
import org.smart4j.chapter4.staticproxy.Hello;
import org.smart4j.chapter4.staticproxy.HelloImpl;
import org.smart4j.chapter4.staticproxy.HelloProxy;

/**
 * @author wangjianchun
 * @create 2017/12/26
 */
public class Main {

    public static void main(String[] args) {
        staticProxy();
        System.out.println("==================");
        dynamicProxy();
        System.out.println("==================");
        cglibProxy();
    }

    private static void staticProxy(){
        System.out.println("Static Proxy");
        Hello helloProxy = new HelloProxy();
        helloProxy.say("Jack");
    }

    private static void dynamicProxy(){
        System.out.println("JDK Dynamic Proxy");
        JDKDynamicProxy dynamicProxy = new JDKDynamicProxy(new HelloImpl());
        Hello helloProxy = dynamicProxy.getProxy();
        helloProxy.say("Jack");
    }

    private static void cglibProxy(){
        System.out.println("CGLib Dynamic Proxy");
        Hello helloProxy = CGLibDynamicProxy.getInstance().getProxy(HelloImpl.class);
        helloProxy.say("Jack");
    }

}
