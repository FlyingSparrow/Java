package com.sparrow.weatherstation;

import com.sparrow.observer.DisplayElement;

import java.util.Observable;
import java.util.Observer;

/**
 * @author wangjianchun
 * @create 2018/7/23
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private Observable observable;

    public CurrentConditionsDisplay(Observable observable){
        this.observable = observable;
        this.observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherData){
            WeatherData weatherData = (WeatherData) o;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("Current conditions: "+temperature+"F degrees and "+humidity+"% humidity");
    }
}
