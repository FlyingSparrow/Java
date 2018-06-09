package com.jdjr.opinion.mongodb.entity;

import java.io.Serializable;

/**
 * @author zhangtong
 * Created by on 2017/11/20
 */
public class RelatedEnterpriseMsg implements Serializable {
    private static final long serialVersionUID = 9201034849892179274L;
    private String enterpriseName;
    private String enterpriseId;
    private double overallPressure;

    public RelatedEnterpriseMsg() {
    }

    public RelatedEnterpriseMsg(String enterpriseName, String enterpriseId, double overallPressure) {
        this.enterpriseName = enterpriseName;
        this.enterpriseId = enterpriseId;
        this.overallPressure = overallPressure;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public double getOverallPressure() {
        return overallPressure;
    }

    public void setOverallPressure(double overallPressure) {
        this.overallPressure = overallPressure;
    }
}
