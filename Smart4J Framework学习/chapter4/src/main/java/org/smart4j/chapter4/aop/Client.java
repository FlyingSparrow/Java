package org.smart4j.chapter4.aop;

import org.springframework.aop.framework.ProxyFactory;

/**
 * @author wangjianchun
 * @create 2017/12/26
 */
public class Client {

    public static void main(String[] args) {
        aopWithBeforeAdviceAndAfterAdvice();
        System.out.println("==================");
        aopWithAroundAdvice();
        System.out.println("==================");
        aopWithAroundAdviceAndThrowAdvice();
        System.out.println("==================");
        aopWithIntroductionAdvice();
    }

    private static void aopWithBeforeAdviceAndAfterAdvice(){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GreetingImpl());
        proxyFactory.addAdvice(new GreetingBeforeAdvice());
        proxyFactory.addAdvice(new GreetingAfterAdvice());

        Greeting greeting = (Greeting) proxyFactory.getProxy();
        greeting.sayHello("Jack");
    }

    private static void aopWithAroundAdvice(){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GreetingImpl());
        proxyFactory.addAdvice(new GreetingAroundAdvice());

        Greeting greeting = (Greeting) proxyFactory.getProxy();
        greeting.sayHello("Jack");
    }

    private static void aopWithAroundAdviceAndThrowAdvice(){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GreetingImpl());
        proxyFactory.addAdvice(new GreetingAroundAdvice());
        proxyFactory.addAdvice(new GreetingThrowAdvice());

        Greeting greeting = (Greeting) proxyFactory.getProxy();
        greeting.sayHello("Jack");
    }

    private static void aopWithIntroductionAdvice(){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GreetingImpl());
        proxyFactory.addAdvice(new GreetingAroundAdvice());
        proxyFactory.addAdvice(new GreetingThrowAdvice());
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvice(new GreetingIntroAdvice());

        GreetingImpl greeting = (GreetingImpl) proxyFactory.getProxy();
        greeting.sayHello("Jack");
        System.out.println("-----------------");
        Apology apology = (Apology) greeting;
        apology.saySorry("Jack");
    }
}
