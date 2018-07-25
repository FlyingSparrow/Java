package com.sparrow.command.impl;

import com.sparrow.command.Command;
import com.sparrow.device.GarageDoor;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class GarageDoorOpenCommand implements Command {

    private GarageDoor garageDoor;

    public GarageDoorOpenCommand(GarageDoor garageDoor){
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.up();
    }

    @Override
    public void undo() {
        garageDoor.down();
    }
}
