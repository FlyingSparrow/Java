package com.sparrow.chapter11;

/**
 * @author wangjianchun
 * @create 2018/4/23
 */
public class Utils {

    private Utils(){}

    /**
     * 模拟一秒钟延迟
     */
    public static void delay(){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
