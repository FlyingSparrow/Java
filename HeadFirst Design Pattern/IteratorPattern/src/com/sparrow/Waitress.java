package com.sparrow;

import com.sparrow.menu.MenuComponent;

/**
 * @author wangjianchun
 * @create 2018/7/27
 */
public class Waitress {

    private MenuComponent allMenus;

    public Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }

    public void printMenu(){
        allMenus.print();
    }
}
