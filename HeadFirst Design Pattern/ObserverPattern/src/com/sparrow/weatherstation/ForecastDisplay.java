package com.sparrow.weatherstation;

import com.sparrow.observer.DisplayElement;
import com.sparrow.observer.Observer;
import com.sparrow.observer.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjianchun
 * @create 2018/7/23
 */
public class ForecastDisplay implements Observer, DisplayElement {

    private static final float PRESSURE_THRESHOLD = 30;
    private float currentPressure;
    private float previousPressure;
    private List<Float> pressureList = new ArrayList<>();
    private Subject weatherData;

    public ForecastDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        pressureList.add(pressure);
        forecast();
        display();
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

    private void forecast(){

    }
}
