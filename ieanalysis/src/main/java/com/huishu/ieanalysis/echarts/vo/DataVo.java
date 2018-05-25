package com.huishu.ieanalysis.echarts.vo;

import java.io.Serializable;

/**
 * @author wangjianchun
 */
public class DataVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4225774608066629821L;

    private String name;

    private Double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
