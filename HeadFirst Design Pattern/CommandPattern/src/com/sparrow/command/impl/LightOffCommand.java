package com.sparrow.command.impl;

import com.sparrow.command.Command;
import com.sparrow.device.Light;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
