package com.sparrow.device;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class HotTub {

    public HotTub(){
    }

    public void on(){
        System.out.println("Hot tub is on");
    }

    public void off(){
        System.out.println("Hot tub is cooling to 98 degrees");
    }

    public void circulate(){
        System.out.println("Hot tub is circulate");
    }

    public void jetsOn(){
        System.out.println("Hot tub is jets on");
    }

    public void jetsOff(){
        System.out.println("Hot tub is jets off");
    }

    public void setTemperature(int temperature){
        System.out.println("Hot tub temperature set to "+temperature);
    }
}
