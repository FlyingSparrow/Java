package com.sparrow.device;

/**
 * @author wangjianchun
 * @create 2018/7/26
 */
public class Amplifier {

    private Tuner tuner;
    private DvdPlayer dvdPlayer;
    private CdPlayer cdPlayer;
    private int volume;

    public Amplifier(){}

    public Amplifier(Tuner tuner, DvdPlayer dvdPlayer, CdPlayer cdPlayer){
        this.tuner = tuner;
        this.dvdPlayer = dvdPlayer;
        this.cdPlayer = cdPlayer;
    }

    public void on(){
        System.out.println("Top-O-Line Amplifier on");
    }

    public void off(){
        System.out.println("Top-O-Line Amplifier off");
    }

    public void setCd(){
        System.out.println("Top-O-Line Amplifier setting CD player to Top-O-Line CD Player");
    }

    public void setDvd(){
        System.out.println("Top-O-Line Amplifier setting DVD player to Top-O-Line DVD Player");
    }

    public void setStereoSound(){
        System.out.println("Top-O-Line Amplifier setting DVD player to Top-O-Line DVD Player");
    }

    public void setSurroundSound(){
        System.out.println("Top-O-Line Amplifier surround sound on (5 speakers, 1 subwoofer)");
    }

    public void setTuner(){
    }

    public void setVolume(int volume){
        System.out.println("Top-O-Line Amplifier setting volume to 5");
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
