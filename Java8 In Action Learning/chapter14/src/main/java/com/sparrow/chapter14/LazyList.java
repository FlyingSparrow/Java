package com.sparrow.chapter14;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author wangjianchun
 * @create 2018/5/4
 */
public class LazyList<T> implements MyList<T> {

    private final T head;
    private final Supplier<LazyList<T>> tail;

    public LazyList(T head, Supplier<LazyList<T>> tail){
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public LazyList<T> tail() {
        return tail.get();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public LazyList<T> filter(Predicate<T> predicate){
        return isEmpty() ? this :
                predicate.test(head()) ? new LazyList<T>(head(), () -> tail().filter(predicate))
                        : tail().filter(predicate);
    }
}
