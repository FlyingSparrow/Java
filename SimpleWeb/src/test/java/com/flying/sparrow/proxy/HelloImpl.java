package com.flying.sparrow.proxy;

import com.flying.sparrow.HelperLoader;

/**
 * Created by wangjianchun on 2017/11/9.
 */
public class HelloImpl implements Hello {

    @Override
    public void say(String name) {
        System.out.println("Hello! "+name);
    }
}
