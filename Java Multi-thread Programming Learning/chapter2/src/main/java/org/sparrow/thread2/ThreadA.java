package org.sparrow.thread2;

/**
 * @author wangjianchun
 * @create 2018/3/17
 */
public class ThreadA extends Thread {

    private HasSelfPrivateNum hasSelfPrivateNum;

    public ThreadA(HasSelfPrivateNum hasSelfPrivateNum){
        super();
        this.hasSelfPrivateNum = hasSelfPrivateNum;
    }

    @Override
    public void run() {
        super.run();
        hasSelfPrivateNum.add("a");
    }
}
