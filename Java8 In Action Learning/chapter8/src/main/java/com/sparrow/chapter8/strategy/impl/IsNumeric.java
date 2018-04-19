package com.sparrow.chapter8.strategy.impl;

import com.sparrow.chapter8.strategy.ValidationStrategy;

/**
 * @author wangjianchun
 * @create 2018/4/13
 */
public class IsNumeric implements ValidationStrategy {

    @Override
    public boolean execute(String string) {
        return string.matches("\\d+");
    }
}
