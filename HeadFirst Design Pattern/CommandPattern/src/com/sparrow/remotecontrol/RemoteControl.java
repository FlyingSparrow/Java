package com.sparrow.remotecontrol;

import com.sparrow.command.Command;
import com.sparrow.command.impl.NoCommand;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class RemoteControl {

    private Command[] onCommands;
    private Command[] offCommands;

    public RemoteControl(){
        onCommands = new Command[7];
        offCommands = new Command[7];

        Command noCommand = new NoCommand();
        for(int i=0; i<onCommands.length; i++){
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand){
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot){
        onCommands[slot].execute();
    }

    public void offButtonWasPushed(int slot){
        offCommands[slot].execute();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\n----- Remote Control -----\n");
        for(int i=0;i <onCommands.length; i++){
            result.append("[slot "+i+"] ").append(onCommands[i].getClass().getName())
                .append("   ").append(offCommands[i].getClass().getName()).append("\n");
        }

        return result.toString();
    }
}
