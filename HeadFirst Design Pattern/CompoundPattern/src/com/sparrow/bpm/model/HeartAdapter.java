package com.sparrow.bpm.model;

import com.sparrow.bpm.observer.BPMObserver;
import com.sparrow.bpm.observer.BeatObserver;

/**
 * @author wangjianchun
 * @create 2018/8/8
 */
public class HeartAdapter implements BeatModelInterface {

    private HeartModelInterface heart;

    public HeartAdapter(HeartModelInterface heart) {
        this.heart = heart;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void on() {
    }

    @Override
    public void off() {
    }

    @Override
    public void setBPM(int bpm) {
    }

    @Override
    public int getBPM() {
        return heart.getHeartRate();
    }

    @Override
    public void registerObserver(BeatObserver observer) {
        heart.registerObserver(observer);
    }

    @Override
    public void removeObserver(BeatObserver observer) {
        heart.removeObserver(observer);
    }

    @Override
    public void registerObserver(BPMObserver observer) {
        heart.registerObserver(observer);
    }

    @Override
    public void removeObserver(BPMObserver observer) {
        heart.removeObserver(observer);
    }
}
