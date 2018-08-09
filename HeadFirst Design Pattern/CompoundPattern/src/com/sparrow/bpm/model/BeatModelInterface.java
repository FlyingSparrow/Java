package com.sparrow.bpm.model;

import com.sparrow.bpm.observer.BPMObserver;
import com.sparrow.bpm.observer.BeatObserver;

/**
 * @author wangjianchun
 * @create 2018/8/8
 */
public interface BeatModelInterface {

    void initialize();

    void on();

    void off();

    void setBPM(int bpm);

    int getBPM();

    void registerObserver(BeatObserver observer);

    void removeObserver(BeatObserver observer);

    void registerObserver(BPMObserver observer);

    void removeObserver(BPMObserver observer);
}
