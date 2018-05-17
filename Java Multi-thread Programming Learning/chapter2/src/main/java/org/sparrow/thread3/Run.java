package org.sparrow.thread3;

/**
 * @author wangjianchun
 * @create 2018/3/17
 */
public class Run {

    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        ThreadA threadA = new ThreadA(myObject);
        threadA.setName("A");
        ThreadB threadB = new ThreadB(myObject);
        threadB.setName("B");
        threadA.start();
        threadB.start();
    }

}
