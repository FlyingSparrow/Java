package com.sparrow.bpm.test;

import com.sparrow.bpm.controller.BeatController;
import com.sparrow.bpm.controller.ControllerInterface;
import com.sparrow.bpm.model.BeatModel;
import com.sparrow.bpm.model.BeatModelInterface;

/**
 * @author wangjianchun
 * @create 2018/8/9
 */
public class DJTestDrive {

    public static void main(String[] args) {
        BeatModelInterface model = new BeatModel();
        ControllerInterface controller = new BeatController(model);
    }
}
