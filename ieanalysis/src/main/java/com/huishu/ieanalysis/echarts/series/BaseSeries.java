package com.huishu.ieanalysis.echarts.series;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuwei on 2016/12/26
 */
public abstract class BaseSeries<T> {

    private String type;

    private String name;

    private String stack;

    private List<T> data;

    public BaseSeries(String type) {
        this.type = type;
    }

    public String getStack() {
        return stack;
    }

    public BaseSeries<T> setStack(String stack) {
        this.stack = stack;
        return this;
    }

    public String getType() {
        return type;
    }

    public List<T> getData() {
        return data;
    }

    public BaseSeries<T> setData(List<T> data) {
        this.data = data;
        return this;
    }

    public BaseSeries<T> addData(T data) {
        if (this.data == null) {
            this.data = new ArrayList<>();
        }
        this.data.add(data);
        return this;
    }

    public String getName() {
        return name;
    }

    public BaseSeries<T> setName(String name) {
        this.name = name;
        return this;
    }

    public static class SerieData<T> {

        private T value;

        private String name;

        public SerieData(String name, T value) {
            this.name = name;
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
