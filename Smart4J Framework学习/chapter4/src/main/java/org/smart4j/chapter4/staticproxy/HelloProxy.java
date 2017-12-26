package org.smart4j.chapter4.staticproxy;

/**
 * @author wangjianchun
 * @create 2017/12/26
 */
public class HelloProxy implements Hello {

    private Hello hello;

    public HelloProxy(){
        hello = new HelloImpl();
    }

    @Override
    public void say(String name) {
        before();
        hello.say(name);
        after();
    }

    private void before(){
        System.out.println("Before");
    }

    private void after(){
        System.out.println("After");
    }
}
