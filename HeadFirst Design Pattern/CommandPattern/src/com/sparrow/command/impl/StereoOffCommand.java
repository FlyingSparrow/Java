package com.sparrow.command.impl;

import com.sparrow.command.Command;
import com.sparrow.device.Stereo;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class StereoOffCommand implements Command {

    private Stereo stereo;

    public StereoOffCommand(Stereo stereo){
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.off();
    }

    @Override
    public void undo() {
        stereo.on();
    }
}
