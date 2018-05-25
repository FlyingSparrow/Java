package com.huishu.ieanalysis.echarts.series;


/**
 * @author wangjianchun
 */
public class Radar<T> {

    private String name;

    private String type;

    private Data<T> data;

    public Radar() {
        this.setType("radar");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Data<T> getData() {
        return data;
    }

    public void setData(Data<T> data) {
        this.data = data;
    }


}
