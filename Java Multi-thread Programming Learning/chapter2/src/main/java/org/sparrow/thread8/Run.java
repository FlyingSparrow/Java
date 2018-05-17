package org.sparrow.thread8;

/**
 * @author wangjianchun
 * @create 2018/3/17
 */
public class Run {

    public static void main(String[] args) {
        try {
            Service service = new Service();
            MyThread threadA = new MyThread(service);
            threadA.setName("a");
            threadA.start();
            Thread.sleep(500);
            MyThread threadB = new MyThread(service);
            threadB.setName("b");
            threadB.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
