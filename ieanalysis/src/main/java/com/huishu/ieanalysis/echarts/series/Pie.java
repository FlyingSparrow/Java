package com.huishu.ieanalysis.echarts.series;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuwei on 2016/12/26
 */
public class Pie<T> extends Serie<T> {

    private List<String> radius;

    public Pie() {
        super("pie");
    }

    public List<String> getRadius() {
        return radius;
    }

    public Pie<T> setRadius(List<String> radius) {
        this.radius = radius;
        return this;
    }

    public Pie<T> addRadiu(String radiu) {
        if (radius == null) {
            radius = new ArrayList<>();
        }
        radius.add(radiu);
        return this;
    }


}
