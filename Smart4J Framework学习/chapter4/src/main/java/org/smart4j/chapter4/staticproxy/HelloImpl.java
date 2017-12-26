package org.smart4j.chapter4.staticproxy;

/**
 * @author wangjianchun
 * @create 2017/12/26
 */
public class HelloImpl implements Hello {

    @Override
    public void say(String name) {
        System.out.println("Hello! "+name);
    }
}
