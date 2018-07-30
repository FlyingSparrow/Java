package com.sparrow.iterator.impl;

import com.sparrow.menu.MenuComponent;

import java.util.Iterator;

/**
 * @author wangjianchun
 * @create 2018/7/30
 */
public class CompositeIterator implements Iterator {

    private Iterator<MenuComponent> iterator;

    public CompositeIterator(Iterator<MenuComponent> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Object next() {
        return iterator.next();
    }
}
