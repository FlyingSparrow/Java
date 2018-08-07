package com.sparrow.dynamicproxy.protectionproxy;

/**
 * @author wangjianchun
 * @create 2018/8/6
 */
public interface PersonBean {

    String getName();
    String getGender();
    String getInterests();
    int getHotOrNotRating();

    void setName(String name);
    void setGender(String gender);
    void setInterests(String interests);
    void setHotOrNotRating(int rating);
}
