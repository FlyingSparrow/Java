package com.sparrow.virtualproxy.imageproxy.state;

import java.awt.*;

/**
 * @author wangjianchun
 * @create 2018/8/7
 */
public interface ImageState {

    void paintIcon(Component c, Graphics g, int x, int y);
    int getIconWidth();
    int getIconHeight();

}
