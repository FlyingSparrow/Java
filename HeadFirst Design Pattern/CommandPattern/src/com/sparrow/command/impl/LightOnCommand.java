package com.sparrow.command.impl;

import com.sparrow.command.Command;
import com.sparrow.device.Light;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
