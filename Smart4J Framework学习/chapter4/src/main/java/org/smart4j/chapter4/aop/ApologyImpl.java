package org.smart4j.chapter4.aop;

/**
 * @author wangjianchun
 * @create 2017/12/27
 */
public class ApologyImpl implements Apology {

    @Override
    public void saySorry(String name) {
        System.out.println("Sorry! "+name);
    }
}
