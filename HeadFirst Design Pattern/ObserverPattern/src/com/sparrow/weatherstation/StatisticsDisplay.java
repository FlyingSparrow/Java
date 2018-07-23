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
public class StatisticsDisplay implements Observer, DisplayElement {

    private float max;
    private float min;
    private float avg;
    private List<Float> temperatureList = new ArrayList<>();
    private Observable observable;

    public StatisticsDisplay(Observable observable) {
        this.observable = observable;
        this.observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherData){
            WeatherData weatherData = (WeatherData) o;
            temperatureList.add(weatherData.getTemperature());
            calculateTemperature();
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("Avg/Max/Min temperature = "+avg+"/"+max+"/"+min);
    }

    private void calculateTemperature(){
        float total = 0;
        if(temperatureList.size() > 0){
            max = temperatureList.get(0);
            min = temperatureList.get(0);
            for(int i=0; i<temperatureList.size(); i++){
                float temperature = temperatureList.get(i);
                total += temperature;
                if(temperature > max){
                    max = temperature;
                }
                if(temperature < min){
                    min = temperature;
                }
            }
            this.avg = total/temperatureList.size();
        }
    }
}
