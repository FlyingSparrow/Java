package com.sparrow.bpm.model;

import com.sparrow.bpm.observer.BPMObserver;
import com.sparrow.bpm.observer.BeatObserver;

/**
 * @author wangjianchun
 * @create 2018/8/8
 */
public interface HeartModelInterface {

    void registerObserver(BeatObserver observer);

    void removeObserver(BeatObserver observer);

    void registerObserver(BPMObserver observer);

    void removeObserver(BPMObserver observer);

    int getHeartRate();
}
