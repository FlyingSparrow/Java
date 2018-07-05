package com.sparrow.app.config;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * IBE 接口实体类
 * @author wangjianchun
 * @create 2018/7/4
 */
public class IBEApi {

    private String type;
    private Boolean enable;
    private String id;
    private String name;
    private String url;
    private String description;

    public IBEApi(String type, Boolean enable, String id, String name, String url, String description){
        this.type = type;
        this.enable = enable;
        this.id = id;
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public Boolean getEnable() {
        return enable;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
