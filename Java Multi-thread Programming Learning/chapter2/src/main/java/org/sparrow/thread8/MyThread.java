package org.sparrow.thread8;

/**
 * @author wangjianchun
 * @create 2018/3/17
 */
public class MyThread extends Thread {

    private Service service;

    public MyThread(Service service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}
