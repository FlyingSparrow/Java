package com.sparrow.adapter;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * @author wangjianchun
 * @create 2018/7/26
 */
public class IteratorEnumeration implements Enumeration {

    private Iterator iterator;

    public IteratorEnumeration(Iterator iterator){
        this.iterator = iterator;
    }

    @Override
    public boolean hasMoreElements() {
        return iterator.hasNext();
    }

    @Override
    public Object nextElement() {
        return iterator.next();
    }
}
