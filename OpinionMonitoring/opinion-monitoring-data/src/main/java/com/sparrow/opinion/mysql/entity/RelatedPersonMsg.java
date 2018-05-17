package com.sparrow.opinion.mysql.entity;

import java.io.Serializable;

/**
 * @author zhangtong
 * Created by on 2017/11/20
 */
public class RelatedPersonMsg implements Serializable {
    private static final long serialVersionUID = 9201034849892179274L;

    private String name;
    private String negativePressureIndex;
    private String pessureProportion;

    public RelatedPersonMsg() {
    }

    public RelatedPersonMsg(String name, String negativePressureIndex, String pessureProportion) {
        this.name = name;
        this.negativePressureIndex = negativePressureIndex;
        this.pessureProportion = pessureProportion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNegativePressureIndex() {
        return negativePressureIndex;
    }

    public void setNegativePressureIndex(String negativePressureIndex) {
        this.negativePressureIndex = negativePressureIndex;
    }

    public String getPessureProportion() {
        return pessureProportion;
    }

    public void setPessureProportion(String pessureProportion) {
        this.pessureProportion = pessureProportion;
    }
}
