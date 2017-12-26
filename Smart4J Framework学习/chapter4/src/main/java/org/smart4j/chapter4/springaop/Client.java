package org.smart4j.chapter4.springaop;

import org.smart4j.chapter4.aop.Greeting;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wangjianchun
 * @create 2017/12/26
 */
public class Client {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop/demo/spring.xml");
        Greeting greeting = (Greeting) context.getBean("greetingProxy");
        greeting.sayHello("Jack");
    }
}
