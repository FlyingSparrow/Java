package com.sparrow.chapter14;

/**
 * @author wangjianchun
 * @create 2018/5/4
 */
public class MyLinkedList<T> implements MyList<T> {

    private final T head;
    private final MyList tail;

    public MyLinkedList(T head, MyList<T> tail){
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
