package com.sparrow.bpm.controller;

/**
 * @author wangjianchun
 * @create 2018/8/8
 */
public interface ControllerInterface {
    void setBPM(int bpm);

    void increaseBPM();

    void decreaseBPM();

    void start();

    void stop();
}
