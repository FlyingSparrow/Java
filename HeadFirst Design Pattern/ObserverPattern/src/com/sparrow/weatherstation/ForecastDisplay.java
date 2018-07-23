package com.sparrow.weatherstation;

import com.sparrow.observer.DisplayElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author wangjianchun
 * @create 2018/7/23
 */
public class ForecastDisplay implements Observer, DisplayElement {

    private static final float PRESSURE_THRESHOLD = 30;
    private float currentPressure;
    private float previousPressure;
    private List<Float> pressureList = new ArrayList<>();
    private Observable observable;

    public ForecastDisplay(Observable observable) {
        this.observable = observable;
        this.observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherData){
            WeatherData weatherData = (WeatherData) o;
            pressureList.add(weatherData.getPressure());
            display();
        }
    }

    @Override
    public void display() {
        int size = pressureList.size();
        if(size == 1){
            currentPressure = pressureList.get(0);
            if(currentPressure > PRESSURE_THRESHOLD){
                System.out.println("Forecast: Improving weather on the way");
            }
        }else if(size > 1){
            currentPressure = pressureList.get(size -1);
            previousPressure = pressureList.get(size - 2);
            if(currentPressure < previousPressure){
                System.out.println("Forecast: Watch out for cooler, rainy weather");
            }else if(currentPressure > previousPressure){
                System.out.println("Forecast: Improving weather on the way");
            }else {
                System.out.println("Forecast: More of the same");
            }
        }
    }
}
