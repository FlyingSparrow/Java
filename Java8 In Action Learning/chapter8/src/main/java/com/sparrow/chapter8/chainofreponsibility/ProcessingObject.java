package com.sparrow.chapter8.chainofreponsibility;

/**
 * @author wangjianchun
 * @create 2018/4/19
 */
public abstract class ProcessingObject<T> {

    protected ProcessingObject<T> successor;

    public void setSuccessor(ProcessingObject<T> successor) {
        this.successor = successor;
    }

    public T handle(T input){
        T r = handleWork(input);
        if(successor != null){
            return successor.handle(r);
        }
        return r;
    }

    protected abstract T handleWork(T input);
}
