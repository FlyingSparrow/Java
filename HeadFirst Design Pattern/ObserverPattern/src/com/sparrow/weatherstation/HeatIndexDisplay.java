package com.sparrow.weatherstation;

import com.sparrow.observer.DisplayElement;

import java.util.Observable;
import java.util.Observer;

/**
 * @author wangjianchun
 * @create 2018/7/23
 */
public class HeatIndexDisplay implements Observer, DisplayElement {

    private float heatIndex;
    private Observable observable;

    public HeatIndexDisplay(Observable observable) {
        this.observable = observable;
        this.observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) o;
            this.heatIndex = computeHeatIndex(weatherData.getTemperature(), weatherData.getHumidity());
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("Heat index is " + heatIndex);
    }

    /**
     * 计算酷热指数
     *
     * @param t  温度
     * @param rh 相对湿度
     * @return
     */
    private float computeHeatIndex(float t, float rh) {
        float index = (float) ((16.923 + (0.185212 * t) + (5.37941 * rh) - (0.100254 * t * rh) +
                (0.00941695 * (t * t)) + (0.00728898 * (rh * rh)) +
                (0.000345372 * (t * t * rh)) - (0.000814971 * (t * rh * rh)) +
                (0.0000102102 * (t * t * rh * rh)) - (0.000038646 * (t * t * t)) + (0.0000291583 *
                (rh * rh * rh)) + (0.00000142721 * (t * t * t * rh)) +
                (0.000000197483 * (t * rh * rh * rh)) - (0.0000000218429 * (t * t * t * rh * rh)) +
                0.000000000843296 * (t * t * rh * rh * rh)) -
                (0.0000000000481975 * (t * t * t * rh * rh * rh)));
        return index;
    }
}
