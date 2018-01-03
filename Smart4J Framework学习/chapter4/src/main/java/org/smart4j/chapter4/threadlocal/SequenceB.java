package org.smart4j.chapter4.threadlocal;

/**
 * @author wangjianchun
 * @create 2018/1/3
 */
public class SequenceB implements Sequence {

    private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    @Override
    public int getNumber() {
        numberContainer.set(numberContainer.get()+1);
        return numberContainer.get();
    }

    public static void main(String[] args) {
        SequenceB sequenceB = new SequenceB();

        ClientThread clientThread1 = new ClientThread(sequenceB);
        ClientThread clientThread2 = new ClientThread(sequenceB);
        ClientThread clientThread3 = new ClientThread(sequenceB);

        clientThread1.start();
        clientThread2.start();
        clientThread3.start();
    }
}
