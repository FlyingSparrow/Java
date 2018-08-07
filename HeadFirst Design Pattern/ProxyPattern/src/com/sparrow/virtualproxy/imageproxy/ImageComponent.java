package com.sparrow.virtualproxy.imageproxy;

import javax.swing.*;
import java.awt.*;

/**
 * @author wangjianchun
 * @create 2018/8/6
 */
public class ImageComponent extends JComponent {

    private Icon icon;

    public ImageComponent(Icon icon) {
        this.icon = icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        int x = (800 - width)/2;
        int y = (600 - height)/2;
        icon.paintIcon(this, g, x, y);
    }
}
