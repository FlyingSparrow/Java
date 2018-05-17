package org.sparrow.thread;

/**
 * @author wangjianchun
 * @create 2018/3/17
 */
public class Run {

    public static void main(String[] args) {
        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
        ThreadA threadA = new ThreadA(hasSelfPrivateNum);
        threadA.start();
        ThreadB threadB = new ThreadB(hasSelfPrivateNum);
        threadB.start();
    }

}
