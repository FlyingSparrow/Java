package com.sparrow.virtualproxy.imageproxy;

import com.sparrow.virtualproxy.imageproxy.state.ImageState;
import com.sparrow.virtualproxy.imageproxy.state.impl.ImageLoaded;
import com.sparrow.virtualproxy.imageproxy.state.impl.ImageNotLoaded;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author wangjianchun
 * @create 2018/8/4
 */
public class ImageProxy implements Icon {

    private ImageIcon imageIcon;
    private URL imageUrl;
    private Thread retrievalThread;
    private boolean retrieving = false;

    private ImageState imageNotLoaded = new ImageNotLoaded(this);
    private ImageState imageState = imageNotLoaded;

    public ImageProxy(URL url){
        this.imageUrl = url;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        imageState.paintIcon(c, g, x, y);
        if(!retrieving){
            retrieving = true;
            retrievalThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        imageIcon = new ImageIcon(imageUrl, "CD Cover");
                        imageState = new ImageLoaded(imageIcon);
                        imageState.paintIcon(c, g, x, y);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            retrievalThread.start();
        }
    }

    @Override
    public int getIconWidth() {
        return imageState.getIconWidth();
    }

    @Override
    public int getIconHeight() {
        return imageState.getIconHeight();
    }
}
