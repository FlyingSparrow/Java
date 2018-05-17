package org.sparrow.thread7;

/**
 * @author wangjianchun
 * @create 2018/3/17
 */
public class Main {

    public int i=10;
    public synchronized void operateIMainMethod(){
        try {
            i--;
            System.out.println("main print i="+i);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
