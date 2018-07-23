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
public class StatisticsDisplay implements Observer, DisplayElement {

    private float max;
    private float min;
    private float avg;
    private List<Float> temperatureList = new ArrayList<>();
    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        temperatureList.add(temperature);
        calculateTemperature();
        display();
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
