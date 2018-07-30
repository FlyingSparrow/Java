package com.sparrow.menu.impl;

import com.sparrow.iterator.impl.CompositeIterator;
import com.sparrow.menu.MenuComponent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author wangjianchun
 * @create 2018/7/27
 */
public class Menu extends MenuComponent {

    private List<MenuComponent> menuComponents = new ArrayList<>();
    private String name;
    private String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int i) {
        return menuComponents.get(i);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void print() {
        System.out.print("\n"+getName());
        System.out.println(", "+getDescription());
        System.out.println("----------------------");

        //确保先打印当前菜单的菜单项，打印完当前菜单的菜单项以后，再打印子菜单的菜单项
        List<MenuComponent> subMenu = new ArrayList<>();
        for(MenuComponent menuComponent : menuComponents){
            if(menuComponent instanceof MenuItem){
                menuComponent.print();
            }else{
                subMenu.add(menuComponent);
            }
        }

        Iterator<MenuComponent> iterator = subMenu.iterator();
        while (iterator.hasNext()){
            MenuComponent menuComponent = iterator.next();
            menuComponent.print();
        }
    }

    public Iterator createIterator(){
        return new CompositeIterator(menuComponents.iterator());
    }
}
