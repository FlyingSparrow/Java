package com.huishu.ieanalysis.echarts.series;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author wangjianchun
 */
public class Map {

    private String type;

    private String name;

    private String mapType;

    private JSONObject label;

    private List<?> data;

    public Map() {
        this.type = "map";
        this.mapType = "china";
        label = new JSONObject();
        JSONObject show = new JSONObject();
        show.put("show", true);
        label.put("normal", show);
    }

    public String getType() {
        return type;
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    public List<?> getData() {
        return data;
    }

    public Map setData(List<?> data) {
        this.data = data;
        return this;
    }

    public String getName() {
        return name;
    }

    public Map setName(String name) {
        this.name = name;
        return this;
    }

    public JSONObject getLabel() {
        return label;
    }

    public void setLabel(JSONObject label) {
        this.label = label;
    }

}
