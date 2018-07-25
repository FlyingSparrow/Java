package com.sparrow.command.impl;

import com.sparrow.command.Command;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class MacroCommand implements Command {

    private Command[] commands;

    public MacroCommand(Command[] commands){
        this.commands = commands;
    }

    @Override
    public void execute() {
        for(int i=0; i<commands.length; i++){
            commands[i].execute();
        }
    }

    @Override
    public void undo() {
        for(int i=0; i<commands.length; i++){
            commands[i].undo();
        }
    }
}
