package com.sparrow.chapter17;

import java.util.Spliterator;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

/**
 * @author wangjianchun
 * @create 2018/5/8
 */
public class BlockingQueueSpliterator<T> implements Spliterator<T> {

    private final BlockingQueue<T> queue;

    public BlockingQueueSpliterator(BlockingQueue<T> queue) {
        this.queue = queue;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        T t;
        while (true){
            try {
                t = queue.take();
                break;
            } catch (InterruptedException e) {
            }
        }
        if(t != ForkingStreamConsumer.END_OF_STREAM){
            action.accept(t);
            return true;
        }
        return false;
    }

    @Override
    public Spliterator<T> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return 0;
    }

    @Override
    public int characteristics() {
        return 0;
    }
}
