package org.sparrow.thread5;

/**
 * @author wangjianchun
 * @create 2018/3/17
 */
public class Run {

    public static void main(String[] args) {
        try {
            PublicVar publicVar = new PublicVar();
            ThreadA threadA = new ThreadA(publicVar);
            threadA.start();
            //打印结果受此值大小影响
            Thread.sleep(2000);
            publicVar.getValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
