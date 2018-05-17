package org.sparrow.thread3;

/**
 * @author wangjianchun
 * @create 2018/3/17
 */
public class ThreadB extends Thread {

    private MyObject myObject;

    public ThreadB(MyObject myObject){
        super();
        this.myObject = myObject;
    }

    @Override
    public void run() {
        super.run();
        myObject.methodA();
    }
}
