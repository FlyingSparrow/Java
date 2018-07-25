package com.sparrow.command.impl;

import com.sparrow.command.Command;
import com.sparrow.device.TV;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class TVOffCommand implements Command {

    private TV tv;

    public TVOffCommand(TV tv){
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.off();
    }

    @Override
    public void undo() {
        tv.on();
    }
}
