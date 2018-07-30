package com.sparrow.iterator.impl;

import com.sparrow.iterator.Iterator;
import com.sparrow.menu.impl.MenuItem;

import java.util.List;

/**
 * @author wangjianchun
 * @create 2018/7/27
 */
public class PancakeMenuIterator implements Iterator {

    private List items;
    private int position = 0;

    public PancakeMenuIterator(List items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return (position < items.size());
    }

    @Override
    public Object next() {
        MenuItem menuItem = (MenuItem) items.get(position);
        position = position+1;
        return menuItem;
    }
}
