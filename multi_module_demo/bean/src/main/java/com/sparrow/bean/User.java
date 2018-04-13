package com.sparrow.bean;

import java.io.Serializable;

/**
 * @author wangjianchun
 * @create 2018/4/13
 */
public class User implements Serializable {

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
