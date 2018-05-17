package org.sparrow.thread6;

/**
 * @author wangjianchun
 * @create 2018/3/17
 */
public class Service {

    public synchronized void service1(){
        System.out.println("service1");
        service2();
    }

    public synchronized void service2() {
        System.out.println("service2");
    }

    public synchronized void service3() {
        System.out.println("service3");
    }

}
