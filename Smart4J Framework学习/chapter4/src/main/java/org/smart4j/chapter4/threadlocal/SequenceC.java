package org.smart4j.chapter4.threadlocal;

/**
 * @author wangjianchun
 * @create 2018/1/3
 */
public class SequenceC implements Sequence {

    private static MyThreadLocal<Integer> numberContainer = new MyThreadLocal<Integer>(){
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
        SequenceC sequenceC = new SequenceC();

        ClientThread clientThread1 = new ClientThread(sequenceC);
        ClientThread clientThread2 = new ClientThread(sequenceC);
        ClientThread clientThread3 = new ClientThread(sequenceC);

        clientThread1.start();
        clientThread2.start();
        clientThread3.start();
    }
}
