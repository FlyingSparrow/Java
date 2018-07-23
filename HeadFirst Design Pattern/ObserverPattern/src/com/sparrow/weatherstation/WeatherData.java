package com.sparrow.weatherstation;

import com.sparrow.observer.Observer;
import com.sparrow.observer.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjianchun
 * @create 2018/7/23
 */
public class WeatherData implements Subject {

    private List observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData(){
        observers = new ArrayList();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int index = observers.indexOf(observer);
        if(index >= 0){
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        for(int i=0; i<observers.size(); i++){
            Observer observer = (Observer) observers.get(i);
            observer.update(temperature, humidity, pressure);
        }
    }

    public void measurementsChanged(){
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
