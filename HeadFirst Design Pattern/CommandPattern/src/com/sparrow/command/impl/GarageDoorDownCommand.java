package com.sparrow.command.impl;

import com.sparrow.command.Command;
import com.sparrow.device.GarageDoor;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class GarageDoorDownCommand implements Command {

    private GarageDoor garageDoor;

    public GarageDoorDownCommand(GarageDoor garageDoor){
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.down();
    }

    @Override
    public void undo() {
        garageDoor.up();
    }
}
