package com.sparrow.chapter9;

/**
 * @author wangjianchun
 * @create 2018/4/20
 */
public interface Moveable {

    int getX();
    int getY();
    void setX(int x);
    void setY(int y);

    default void moveHorizontally(int distance){
        setX(getX()+distance);
    }
    default void moveVertically(int distance){
        setY(getY()+distance);
    }
}
