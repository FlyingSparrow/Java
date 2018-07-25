package com.sparrow.device;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class GarageDoor {

    private String location;

    public GarageDoor(String location){
        this.location = location;
    }

    public void up(){
        System.out.println("Garage Door is Open");
    }

    public void down(){
        System.out.println("Garage Door is Close");
    }

    public void stop(){
        System.out.println("Garage Door is Stop");
    }

    public void lightOn(){
        System.out.println("The light of the garage door is on");
    }

    public void lightOff(){
        System.out.println("The light of the garage door is off");
    }
}
