package org.sparrow.thread.daemonthread;

/**
 * @author wangjianchun
 * @create 2018/3/17
 */
public class MyThread extends Thread {

    private int i = 0;

    @Override
    public void run() {
        try {
            while (true){
                i++;
                System.out.println("currentThread:"+Thread.currentThread().getName()+", i="+i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
