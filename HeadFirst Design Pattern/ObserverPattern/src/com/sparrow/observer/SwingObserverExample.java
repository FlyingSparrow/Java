package com.sparrow.observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author wangjianchun
 * @create 2018/7/23
 */
public class SwingObserverExample {

    private JFrame frame;

    public static void main(String[] args) {
        //说明：在Idea中，不会显示SWING窗口
        SwingObserverExample example = new SwingObserverExample();
        example.go();
    }

    public void go(){
        frame = new JFrame();

        JButton button = new JButton("Should I do it?");
        button.addActionListener(new AngelListener());
        button.addActionListener(new DevilListener());
        frame.getContentPane().add(BorderLayout.CENTER, button);
    }

    class AngelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Don't do it, you might regret it!");
        }
    }

    class DevilListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Come on, do it!");
        }
    }
}
