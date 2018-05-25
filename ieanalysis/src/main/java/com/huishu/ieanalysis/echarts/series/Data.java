package com.huishu.ieanalysis.echarts.series;

import java.util.List;

/**
 * @author wangjianchun
 */
public class Data<T> {

    private String name;

    List<T> value;

    public List<T> getValue() {
        return value;
    }

    public void setValue(List<T> value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
