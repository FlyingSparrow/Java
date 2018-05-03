package com.sparrow.chapter11;

import java.util.Random;

/**
 * @author wangjianchun
 * @create 2018/4/23
 */
public class Utils {

    private static final Random random = new Random();

    private Utils(){}

    /**
     * 模拟生成0.5秒至2.5秒随机延迟
     */
    public static void randomDelay(){
        try {
            int delay = 500+random.nextInt(2000);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
