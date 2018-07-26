package com.sparrow.device;

/**
 * @author wangjianchun
 * @create 2018/7/26
 */
public class DvdPlayer {

    private Amplifier amplifier;
    private String movie;

    public DvdPlayer(Amplifier amplifier){
        this.amplifier = amplifier;
    }

    public void on(){
        System.out.println("Top-O-Line DVD Player on");
    }

    public void off(){
        System.out.println("Top-O-Line DVD Player off");
    }

    public void eject(){
        System.out.println("Top-O-Line DVD Player eject");
    }

    public void pause(){
        System.out.println("Top-O-Line DVD Player pause");
    }

    public void play(){
        System.out.println("Top-O-Line DVD Player play");
    }

    public void play(String movie) {
        this.movie = movie;
        System.out.println("Top-O-Line DVD Player playing \""+movie+"\"");
    }

    public void setSurroundAudio(){

    }

    public void setTwoChannelAudio(){

    }

    public void stop(){
        System.out.println("Top-O-Line DVD Player stopped \""+movie+"\"");
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
