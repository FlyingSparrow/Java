package com.sparrow.chapter14;

/**
 * @author wangjianchun
 * @create 2018/5/4
 */
public class Empty<T> implements MyList {

    @Override
    public Object head() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MyList tail() {
        throw new UnsupportedOperationException();
    }
}
