package org.sparrow.thread.interrupt;

/**
 * @author wangjianchun
 * @create 2018/2/6
 */
public class Run {

    public static void main(String[] args) {
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
//            MyThread.currentThread().interrupt();
            System.out.println("是否停止1？ ="+Thread.interrupted());
            System.out.println("是否停止2？ ="+Thread.interrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
