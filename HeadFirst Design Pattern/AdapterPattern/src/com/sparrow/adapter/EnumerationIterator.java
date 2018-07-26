package com.sparrow.adapter;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * @author wangjianchun
 * @create 2018/7/26
 */
public class EnumerationIterator implements Iterator {

    private Enumeration enumeration;

    public EnumerationIterator(Enumeration enumeration){
        this.enumeration = enumeration;
    }

    @Override
    public void remove() {
        //说明：下面的两种方式的效果是一样的
//        Iterator.super.remove();
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public Object next() {
        return enumeration.nextElement();
    }
}
