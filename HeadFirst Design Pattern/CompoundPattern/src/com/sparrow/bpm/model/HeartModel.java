package com.sparrow.bpm.model;

import com.sparrow.bpm.observer.BPMObserver;
import com.sparrow.bpm.observer.BeatObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author wangjianchun
 * @create 2018/8/8
 */
public class HeartModel implements HeartModelInterface, Runnable {

    private List<BeatObserver> beatObservers = new ArrayList<>();
    private List<BPMObserver> bpmObservers = new ArrayList<>();
    private int time = 1000;
    private Random random = new Random(System.currentTimeMillis());
    private Thread thread;

    public HeartModel() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void registerObserver(BeatObserver observer) {
        beatObservers.add(observer);
    }

    @Override
    public void removeObserver(BeatObserver observer) {
        int index = beatObservers.indexOf(observer);
        if(index >= 0){
            beatObservers.remove(index);
        }
    }

    @Override
    public void registerObserver(BPMObserver observer) {
        bpmObservers.add(observer);
    }

    @Override
    public void removeObserver(BPMObserver observer) {
        int index = bpmObservers.indexOf(observer);
        if(index >= 0){
            bpmObservers.remove(index);
        }
    }

    private void notifyBeatObservers() {
        for (int i = 0; i < beatObservers.size(); i++) {
            BeatObserver observer = beatObservers.get(i);
            observer.updateBeat();
        }
    }

    private void notifyBPMObservers() {
        for (int i = 0; i < bpmObservers.size(); i++) {
            BPMObserver observer = bpmObservers.get(i);
            observer.updateBPM();
        }
    }

    @Override
    public int getHeartRate(){
        return 60000/time;
    }

    @Override
    public void run() {
        int lastrate = -1;

        for(;;){
            int change = random.nextInt(10);
            if(random.nextInt(2) == 0){
                change = 0-change;
            }
            int rate = 60000/(time+change);
            if(rate < 120 && rate > 50){
                time += change;
                notifyBeatObservers();
                if(rate != lastrate){
                    lastrate = rate;
                    notifyBPMObservers();
                }
            }
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
