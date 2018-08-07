package com.sparrow.virtualproxy.imageproxy.state.impl;

import com.sparrow.virtualproxy.imageproxy.state.ImageState;

import javax.swing.*;
import java.awt.*;

/**
 * @author wangjianchun
 * @create 2018/8/7
 */
public class ImageLoaded implements ImageState {

    private Icon imageIcon;

    public ImageLoaded(Icon imageIcon) {
        this.imageIcon = imageIcon;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        imageIcon.paintIcon(c, g, x, y);
    }

    @Override
    public int getIconWidth() {
        return imageIcon.getIconWidth();
    }

    @Override
    public int getIconHeight() {
        return imageIcon.getIconHeight();
    }
}
