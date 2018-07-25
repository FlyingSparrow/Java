package com.sparrow.remotecontrol;

import com.sparrow.command.Command;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class SimpleRemoteControl {

    Command slot;

    public SimpleRemoteControl(){}

    public void setCommand(Command command) {
        this.slot = command;
    }

    public void buttonWasPressed(){
        slot.execute();
    }
}
