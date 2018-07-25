package com.sparrow.command.impl;

import com.sparrow.command.Command;
import com.sparrow.device.TV;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class TVOnCommand implements Command {

    private TV tv;

    public TVOnCommand(TV tv){
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.on();
    }

    @Override
    public void undo() {
        tv.off();
    }
}
