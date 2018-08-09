package com.sparrow.bpm.view.model;

import javax.swing.*;

/**
 * @author wangjianchun
 * @create 2018/8/8
 */
public class BeatBar extends JProgressBar implements Runnable {
    Thread thread;

    public BeatBar() {
        thread = new Thread(this);
        setMaximum(100);
        thread.start();
    }

    @Override
    public void run() {
        for (; ; ) {
            int value = getValue();
            value = (int) (value * .75);
            setValue(value);
            repaint();
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
        }
    }
}
