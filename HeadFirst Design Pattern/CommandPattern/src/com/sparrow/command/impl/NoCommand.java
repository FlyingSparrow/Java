package com.sparrow.command.impl;

import com.sparrow.command.Command;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class NoCommand implements Command {

    @Override
    public void execute() {
        //do nothing
    }

    @Override
    public void undo() {
        //do nothing
    }
}
