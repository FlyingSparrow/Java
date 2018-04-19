package com.sparrow.chapter8.chainofreponsibility.impl;

import com.sparrow.chapter8.chainofreponsibility.ProcessingObject;

/**
 * @author wangjianchun
 * @create 2018/4/19
 */
public class SpellCheckerProcessing extends ProcessingObject<String> {

    @Override
    protected String handleWork(String input) {
        return input.replaceAll("labda", "lambda");
    }
}
