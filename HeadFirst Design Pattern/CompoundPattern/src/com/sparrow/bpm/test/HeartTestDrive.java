package com.sparrow.bpm.test;

import com.sparrow.bpm.controller.ControllerInterface;
import com.sparrow.bpm.controller.HeartController;
import com.sparrow.bpm.model.HeartModel;
import com.sparrow.bpm.model.HeartModelInterface;

/**
 * @author wangjianchun
 * @create 2018/8/9
 */
public class HeartTestDrive {

    public static void main(String[] args) {
        HeartModelInterface model = new HeartModel();
        ControllerInterface controller = new HeartController(model);
    }
}
