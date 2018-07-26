package com.sparrow;

import com.sparrow.device.*;
import com.sparrow.facade.HomeTheaterFacade;

/**
 * @author wangjianchun
 * @create 2018/7/26
 */
public class HomeTheaterTestDrive {

    public static void main(String[] args) {
        Amplifier amplifier = new Amplifier();
        CdPlayer cdPlayer = new CdPlayer(amplifier);
        DvdPlayer dvdPlayer = new DvdPlayer(amplifier);
        PopcornPopper popcornPopper = new PopcornPopper();
        Projector projector = new Projector(dvdPlayer);
        Screen screen = new Screen();
        TheaterLights theaterLights = new TheaterLights();
        Tuner tuner = new Tuner(amplifier);

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amplifier,
                cdPlayer, dvdPlayer, popcornPopper, projector, screen,
                theaterLights, tuner);
        homeTheater.watchMovie("Raiders of the Lost Ark");
        System.out.println("");
        homeTheater.endMovie();
    }
}
