package org.smart4j.chapter4.threadlocal;

/**
 * @author wangjianchun
 * @create 2018/1/3
 */
public class SequenceA implements Sequence {

    private static int number = 0;

    @Override
    public int getNumber() {
        number = number + 1;
        return number;
    }

    public static void main(String[] args) {
        SequenceA sequenceA = new SequenceA();

        ClientThread clientThread1 = new ClientThread(sequenceA);
        ClientThread clientThread2 = new ClientThread(sequenceA);
        ClientThread clientThread3 = new ClientThread(sequenceA);

        clientThread1.start();
        clientThread2.start();
        clientThread3.start();
    }
}
