package org.sparrow.thread.stop;

/**
 * @author wangjianchun
 * @create 2018/2/6
 */
public class Run {

    public static void main(String[] args) {
        //stopThreadWithException();

        //stopThreadInSleep();

        //stopThreadWithInterruptAndThenSleep();

        stopThreadWithReturn();
    }

    private static void stopThreadWithReturn() {
        try {
            MyThread4 thread4 = new MyThread4();
            thread4.start();
            Thread.sleep(2000);
            thread4.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void stopThreadWithInterruptAndThenSleep() {
        MyThread3 thread = new MyThread3();
        thread.start();
        thread.interrupt();
        System.out.println("end!");
    }

    private static void stopThreadInSleep() {
        try {
            MyThread2 thread = new MyThread2();
            thread.start();
            Thread.sleep(200);
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end");
    }

    private static void stopThreadWithException() {
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end");
    }


}
