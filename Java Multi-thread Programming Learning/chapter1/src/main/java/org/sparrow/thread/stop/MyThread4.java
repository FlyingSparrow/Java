package org.sparrow.thread.stop;

/**
 * @author wangjianchun
 * @create 2018/2/6
 */
public class MyThread4 extends Thread {

    @Override
    public void run() {
        while (true){
            if (this.isInterrupted()){
                System.out.println("停止了！");
                return;
            }
            System.out.println("timer="+System.currentTimeMillis());
        }
    }
}
