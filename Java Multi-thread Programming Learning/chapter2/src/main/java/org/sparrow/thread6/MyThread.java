package org.sparrow.thread6;

/**
 * @author wangjianchun
 * @create 2018/3/17
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        Service service = new Service();
        service.service1();
    }
}
