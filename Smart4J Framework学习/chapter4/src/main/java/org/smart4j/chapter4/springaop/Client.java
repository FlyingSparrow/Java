package org.smart4j.chapter4.springaop;

import org.smart4j.chapter4.aop.Apology;
import org.smart4j.chapter4.aop.Greeting;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wangjianchun
 * @create 2017/12/26
 */
public class Client {

    public static void main(String[] args) {
//        aopWithAroundAdvice();
//        aopWithIntroductionAdvice();
//        aopWithAdvisor();
//        aopWithAroundAdviceAndAutoCreateProxy();
        aopWithAdvisorAndAutoCreateProxy();
    }

    private static void aopWithAroundAdvice(){
        ApplicationContext context = new ClassPathXmlApplicationContext("aop/demo/spring.xml");
        Greeting greeting = (Greeting) context.getBean("greetingProxy");
        greeting.sayHello("Jack");
    }

    private static void aopWithIntroductionAdvice(){
        ApplicationContext context = new ClassPathXmlApplicationContext("aop/demo/spring.xml");
        //注意：转型为目标类，而并非它的 Greeting 接口
        GreetingImpl greetingImpl = (GreetingImpl) context.getBean("greetingProxy");
        greetingImpl.sayHello("Jack");

        //将目标类强制向上转型为 Apology 接口（这是引入增强给我们带来的特性，也就是"接口动态实现"功能）
        Apology apology = (Apology) greetingImpl;
        apology.saySorry("Jack");
    }

    private static void aopWithAdvisor(){
        ApplicationContext context = new ClassPathXmlApplicationContext("aop/demo/spring.xml");
        //注意：转型为目标类，而并非它的 Greeting 接口
        GreetingImpl greetingImpl = (GreetingImpl) context.getBean("greetingProxy");
        greetingImpl.sayHello("Jack");
        System.out.println("------------------");
        greetingImpl.goodMorning("Jack");
        System.out.println("------------------");
        greetingImpl.goodNight("Jack");
    }

    private static void aopWithAroundAdviceAndAutoCreateProxy(){
        ApplicationContext context = new ClassPathXmlApplicationContext("aop/demo/spring.xml");
        GreetingImpl greeting = (GreetingImpl) context.getBean("greetingImpl");
        greeting.sayHello("Jack");
    }

    private static void aopWithAdvisorAndAutoCreateProxy(){
        ApplicationContext context = new ClassPathXmlApplicationContext("aop/demo/spring.xml");
        //注意：转型为目标类，而并非它的 Greeting 接口
        GreetingImpl greetingImpl = (GreetingImpl) context.getBean("greetingImpl");
        greetingImpl.sayHello("Jack");
        System.out.println("------------------");
        greetingImpl.goodMorning("Jack");
        System.out.println("------------------");
        greetingImpl.goodNight("Jack");
    }
}
