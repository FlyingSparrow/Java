package com.sparrow.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @author wangjianchun
 * @create 2018/7/27
 */
public class MyFrame extends JFrame {

    public MyFrame(String title){
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(300,300);
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        String msg = "I rule!!";
        graphics.drawString(msg, 100, 100);
    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame("Head First Design Patterns");
    }
}
