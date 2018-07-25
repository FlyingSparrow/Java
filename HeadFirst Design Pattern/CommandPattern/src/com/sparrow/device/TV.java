package com.sparrow.device;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class TV {

    private String location;

    public TV(String location){
        this.location = location;
    }

    public void on(){
        System.out.println(location+" TV is on");
    }

    public void off(){
        System.out.println(location+" TV is off");
    }

    public void setInputChannel(){
        System.out.println(location+" TV set input channel");
    }

    public void setVolume(int volume){
        System.out.println(location+" TV volume set to "+volume);
    }
}
