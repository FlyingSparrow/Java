package com.sparrow.device;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class Stereo {

    private String location;

    public Stereo(String location) {
        this.location = location;
    }

    public void on(){
        System.out.println(location+" stereo is on");
    }

    public void off(){
        System.out.println(location+" stereo is off");
    }

    public void setCd(){
        System.out.println(location+" stereo is set for CD input");
    }

    public void setDvd(){
        System.out.println(location+" stereo is set for DVD input");
    }

    public void setRadio(){
        System.out.println(location+" The radio of the stereo is set");
    }

    public void setVolume(int volume){
        System.out.println(location+" stereo volume set to "+volume);
    }
}
