package com.sparrow.device;

/**
 * @author wangjianchun
 * @create 2018/7/26
 */
public class Projector {

    private DvdPlayer dvdPlayer;

    public Projector(DvdPlayer dvdPlayer){
        this.dvdPlayer = dvdPlayer;
    }

    public void on(){
        System.out.println("Top-O-Line Projector on");
    }

    public void off(){
        System.out.println("Top-O-Line Projector off");
    }

    public void tvMode(){
        System.out.println("Top-O-Line Projector in tv mode");
    }

    public void wideScreenMode(){
        System.out.println("Top-O-Line Projector in widescreen mode (16x9 aspect ratio)");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
