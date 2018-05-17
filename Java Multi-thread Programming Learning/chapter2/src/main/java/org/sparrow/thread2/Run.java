package org.sparrow.thread2;

/**
 * @author wangjianchun
 * @create 2018/3/17
 */
public class Run {

    public static void main(String[] args) {
//        threadSafeTest();
        threadSafeTest2();
    }

    private static void threadSafeTest() {
        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
        ThreadA threadA = new ThreadA(hasSelfPrivateNum);
        threadA.start();
        ThreadB threadB = new ThreadB(hasSelfPrivateNum);
        threadB.start();
    }

    private static void threadSafeTest2() {
        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
        HasSelfPrivateNum hasSelfPrivateNum2 = new HasSelfPrivateNum();
        ThreadA threadA = new ThreadA(hasSelfPrivateNum);
        threadA.start();
        ThreadB threadB = new ThreadB(hasSelfPrivateNum2);
        threadB.start();
    }

}
