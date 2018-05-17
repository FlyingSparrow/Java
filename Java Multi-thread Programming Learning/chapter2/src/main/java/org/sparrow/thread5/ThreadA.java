package org.sparrow.thread5;

/**
 * @author wangjianchun
 * @create 2018/3/17
 */
public class ThreadA extends Thread {

    private PublicVar publicVar;

    public ThreadA(PublicVar publicVar){
        super();
        this.publicVar = publicVar;
    }

    @Override
    public void run() {
        super.run();
        publicVar.setValue("B", "BB");
    }
}
