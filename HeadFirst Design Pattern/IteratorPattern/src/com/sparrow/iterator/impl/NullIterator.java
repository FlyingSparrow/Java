package com.sparrow.iterator.impl;

import java.util.Iterator;

/**
 * @author wangjianchun
 * @create 2018/7/30
 */
public class NullIterator implements Iterator {

    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object next() {
        throw new UnsupportedOperationException();
    }
}
