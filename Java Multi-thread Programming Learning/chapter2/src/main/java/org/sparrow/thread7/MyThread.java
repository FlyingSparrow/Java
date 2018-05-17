package org.sparrow.thread7;

/**
 * @author wangjianchun
 * @create 2018/3/17
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        Sub sub = new Sub();
        sub.operateISubMethod();
    }
}
