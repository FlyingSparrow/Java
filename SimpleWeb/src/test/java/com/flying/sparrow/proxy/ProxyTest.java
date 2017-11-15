package com.flying.sparrow.proxy;

/**
 * Created by wangjianchun on 2017/11/9.
 */
public class ProxyTest {

    public static void main(String[] args) {
        testDynamicProxy();
        System.out.println("============");
        testCGlibProxy();
    }

    private static void testDynamicProxy(){
        try {
            JDKDynamicProxy dynamicProxy = new JDKDynamicProxy(new HelloImpl());
            /*Hello helloProxy = (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(),
                    hello.getClass().getInterfaces(), dynamicProxy);
            helloProxy.say("Jack");*/

            Hello helloProxy = dynamicProxy.getProxy();
            helloProxy.say("Jack");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private static void testCGlibProxy(){
        Hello helloProxy = CGLibDynamicProxy.getInstance().getProxy(HelloImpl.class);
        helloProxy.say("Jack");
    }

}
