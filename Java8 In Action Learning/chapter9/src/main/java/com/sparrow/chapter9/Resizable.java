package com.sparrow.chapter9;

/**
 * @author wangjianchun
 * @create 2018/4/20
 */
public interface Resizable {

    int getWidth();
    int getHeight();
    void setWidth(int width);
    void setHeight(int height);

    void setAbsoluteSize(int width, int height);

    default void setRelativeSize(int wFactor, int hFactor){
        setAbsoluteSize(getWidth()/wFactor, getHeight()/hFactor);
    }
}
