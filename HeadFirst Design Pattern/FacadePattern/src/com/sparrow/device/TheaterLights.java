package com.sparrow.device;

/**
 * @author wangjianchun
 * @create 2018/7/26
 */
public class TheaterLights {

    public void on(){
        System.out.println("Theater Ceiling Lights on");
    }

    public void off(){
        System.out.println("Theater Ceiling Lights off");
    }

    public void dim(int dim){
        System.out.println("Theater Ceiling Lights dimming to 10%");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
