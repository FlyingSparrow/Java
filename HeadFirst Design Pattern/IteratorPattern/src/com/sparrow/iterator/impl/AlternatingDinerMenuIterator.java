package com.sparrow.iterator.impl;

import com.sparrow.menu.impl.MenuItem;

import java.util.Calendar;
import java.util.Iterator;

/**
 * @author wangjianchun
 * @create 2018/7/27
 */
public class AlternatingDinerMenuIterator implements Iterator {

    private MenuItem[] items;
    private int position;

    public AlternatingDinerMenuIterator(MenuItem[] items) {
        this.items = items;
        Calendar rightNow = Calendar.getInstance();
        position = rightNow.get(Calendar.DAY_OF_WEEK)%2;
    }

    @Override
    public boolean hasNext() {
        if(position >= items.length || items[position] == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Object next() {
        MenuItem item = items[position];
        position = position+2;
        return item;
    }

    @Override
    public void remove() {
       throw new UnsupportedOperationException("Alternating Diner Menu Iterator does not support remove()");
    }
}
