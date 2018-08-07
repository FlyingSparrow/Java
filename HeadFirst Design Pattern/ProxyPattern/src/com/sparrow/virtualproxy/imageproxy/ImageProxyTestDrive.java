package com.sparrow.virtualproxy.imageproxy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author wangjianchun
 * @create 2018/8/4
 */
public class ImageProxyTestDrive {

    private ImageComponent imageComponent;
    private JFrame frame = new JFrame("CD Cover Viewer");
    private JMenuBar menuBar;
    private JMenu menu;
    private Hashtable cds = new Hashtable();

    public static void main(String[] args) throws MalformedURLException {
        ImageProxyTestDrive imageProxyTestDrive = new ImageProxyTestDrive();
    }

    public ImageProxyTestDrive() throws MalformedURLException {
        cds.put("CD One", "https://img13.360buyimg.com/n7/jfs/t904/267/1405620633/1148179/afb58985/559cfc66Nd260b870.jpg");
        cds.put("CD Two", "https://img13.360buyimg.com/n7/jfs/t18586/263/2050197003/213790/a7e88b0a/5ae189dfNe1d83d63.jpg");
        cds.put("CD Three", "https://img12.360buyimg.com/n7/jfs/t5026/267/2301340810/446419/96067d3f/58fd5ac6N4936206e.jpg");
        cds.put("CD Four", "https://img12.360buyimg.com/n7/jfs/t16597/9/1519761059/330400/983454c/5acc726bNf7fe3a49.jpg");
        cds.put("CD Five", "https://img10.360buyimg.com/n7/jfs/t19573/346/391566878/406803/a5b0f766/5a72b60fN5ab05fcd.jpg");
        cds.put("CD Six", "https://img10.360buyimg.com/n7/jfs/t18202/255/1981595567/92819/103446d9/5ae05eb8Ne15dbb07.jpg");
        cds.put("CD Seven", "https://img11.360buyimg.com/n1/s200x200_19740/674f5c84-16d3-4c80-aae6-c3d9a3d08403.jpg");

        URL initialUrl = new URL((String) cds.get("CD Four"));
        menuBar = new JMenuBar();
        menu = new JMenu("Favorite CDs");
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);


        for(Enumeration enumeration = cds.keys(); enumeration.hasMoreElements();){
            String name = (String) enumeration.nextElement();
            JMenuItem menuItem = new JMenuItem(name);
            menu.add(menuItem);
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    imageComponent.setIcon(new ImageProxy(getCDUrl(e.getActionCommand())));
                    frame.repaint();
                }
            });
        }


        Icon icon = new ImageProxy(initialUrl);
        imageComponent = new ImageComponent(icon);
        frame.getContentPane().add(imageComponent);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    private URL getCDUrl(String name) {
        try {
            return new URL((String) cds.get(name));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
