package com.sparrow.iterator.impl;

import com.sparrow.menu.impl.MenuItem;

import java.util.Iterator;

/**
 * @author wangjianchun
 * @create 2018/7/27
 */
public class DinerMenuIterator implements Iterator {

    private MenuItem[] list;
    private int position = 0;

    public DinerMenuIterator(MenuItem[] items) {
        this.list = items;
    }

    @Override
    public boolean hasNext() {
        if(position >= list.length || list[position] == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Object next() {
        MenuItem item = list[position];
        position = position+1;
        return item;
    }

    @Override
    public void remove() {
        if(position <= 0){
            throw new IllegalStateException("You can't remove an item until you've done at least one next()");
        }
        if(list[position-1] != null){
            for(int i = position-1; i<(list.length-1); i++){
                list[i] = list[i+1];
            }
            list[list.length-1] = null;
        }
    }
}
