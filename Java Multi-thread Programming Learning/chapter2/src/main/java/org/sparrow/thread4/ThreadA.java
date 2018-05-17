package org.sparrow.thread4;

/**
 * @author wangjianchun
 * @create 2018/3/17
 */
public class ThreadA extends Thread {

    private MyObject myObject;

    public ThreadA(MyObject myObject){
        super();
        this.myObject = myObject;
    }

    @Override
    public void run() {
        super.run();
        myObject.methodA();
    }
}
