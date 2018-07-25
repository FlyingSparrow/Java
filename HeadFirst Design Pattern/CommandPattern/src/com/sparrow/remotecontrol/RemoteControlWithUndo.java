package com.sparrow.remotecontrol;

import com.sparrow.command.Command;
import com.sparrow.command.impl.NoCommand;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class RemoteControlWithUndo {

    private Command[] onCommands;
    private Command[] offCommands;
    private Command undoCommand;

    public RemoteControlWithUndo(){
        onCommands = new Command[7];
        offCommands = new Command[7];

        Command noCommand = new NoCommand();
        for(int i=0; i<onCommands.length; i++){
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
    }

    public void setCommand(int slot, Command onCommand, Command offCommand){
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot){
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    public void offButtonWasPushed(int slot){
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    public void undoButtonWasPushed(){
        undoCommand.undo();
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
