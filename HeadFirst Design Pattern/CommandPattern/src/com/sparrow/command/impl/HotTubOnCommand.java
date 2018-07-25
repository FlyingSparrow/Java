package com.sparrow.command.impl;

import com.sparrow.command.Command;
import com.sparrow.device.HotTub;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class HotTubOnCommand implements Command {

    private HotTub hotTub;

    public HotTubOnCommand(HotTub hotTub){
        this.hotTub = hotTub;
    }

    @Override
    public void execute() {
        hotTub.on();
    }

    @Override
    public void undo() {
        hotTub.off();
    }
}
