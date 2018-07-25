package com.sparrow.command.impl;

import com.sparrow.command.Command;
import com.sparrow.device.Stereo;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class StereoOnWithDVDCommand implements Command {

    private Stereo stereo;

    public StereoOnWithDVDCommand(Stereo stereo){
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.on();
        stereo.setDvd();
        stereo.setVolume(11);
    }

    @Override
    public void undo() {
        stereo.off();
    }
}
