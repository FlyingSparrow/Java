package com.huishu.ieanalysis.echarts.series;

/**
 * Created by yuwei on 2016/12/26
 */
public class Bar<T> extends BaseSeries<T> {

    private String barGap;

    public Bar() {
        super("bar");
        this.barGap = "0";
    }

    public String getBarGap() {
        return barGap;
    }

    public void setBarGap(String barGap) {
        this.barGap = barGap;
    }
}
