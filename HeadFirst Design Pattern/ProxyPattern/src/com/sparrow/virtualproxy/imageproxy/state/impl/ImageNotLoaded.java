package com.sparrow.virtualproxy.imageproxy.state.impl;

import com.sparrow.virtualproxy.imageproxy.state.ImageState;

import javax.swing.*;
import java.awt.*;

/**
 * @author wangjianchun
 * @create 2018/8/7
 */
public class ImageNotLoaded implements ImageState {

    private Icon imageIcon;

    public ImageNotLoaded(Icon imageIcon) {
        this.imageIcon = imageIcon;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.drawString("Loading CD cover, please wait...", x+300, y+190);
        c.repaint();
    }

    @Override
    public int getIconWidth() {
        return 800;
    }

    @Override
    public int getIconHeight() {
        return 600;
    }
}
