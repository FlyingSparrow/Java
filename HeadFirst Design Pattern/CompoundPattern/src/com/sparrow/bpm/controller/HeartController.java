package com.sparrow.bpm.controller;

import com.sparrow.bpm.model.HeartAdapter;
import com.sparrow.bpm.model.HeartModelInterface;
import com.sparrow.bpm.view.DJView;

/**
 * @author wangjianchun
 * @create 2018/8/9
 */
public class HeartController implements ControllerInterface {

    private HeartModelInterface model;
    private DJView view;

    public HeartController(HeartModelInterface model) {
        this.model = model;
        view = new DJView(this, new HeartAdapter(model));
        view.createView();
        view.createControls();
        view.disableStopMenuItem();
        view.disableStartMenuItem();
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void setBPM(int bpm) {
    }

    @Override
    public void increaseBPM() {
    }

    @Override
    public void decreaseBPM() {
    }

}
