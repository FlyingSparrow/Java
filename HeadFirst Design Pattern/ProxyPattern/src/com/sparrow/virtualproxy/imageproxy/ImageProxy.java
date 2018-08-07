package com.sparrow.virtualproxy.imageproxy;

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

    public ImageProxy(URL url){
        this.imageUrl = url;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if(imageIcon != null){
            imageIcon.paintIcon(c, g, x, y);
        }else {
            g.drawString("Loading CD cover, please wait...", x+300, y+190);
            if(!retrieving){
                retrieving = true;
                retrievalThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            imageIcon = new ImageIcon(imageUrl, "CD Cover");
                            c.repaint();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                retrievalThread.start();
            }
        }
    }

    @Override
    public int getIconWidth() {
        if(imageIcon != null){
            return imageIcon.getIconWidth();
        }else{
            return 800;
        }
    }

    @Override
    public int getIconHeight() {
        if(imageIcon != null){
            return imageIcon.getIconHeight();
        }else{
            return 600;
        }
    }
}
