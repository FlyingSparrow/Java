package com.jdjr.opinion.mongodb.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author wangjianchun
 * @create 2018/4/23
 */
public class OpinionCountInfo implements Serializable {

    /**
     * 企业id
     */
    private String enterpriseId;
    /**
     * 今日舆情总数
     */
    private int totalOpinionCount = 0;
    /**
     * 今日负面舆情数
     */
    private int negativeOpinionCount = 0;
    /**
     * 今日正面舆情数
     */
    private int positiveOpinionCount = 0;

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public int getTotalOpinionCount() {
        return totalOpinionCount;
    }

    public void setTotalOpinionCount(int totalOpinionCount) {
        this.totalOpinionCount = totalOpinionCount;
    }

    public int getNegativeOpinionCount() {
        return negativeOpinionCount;
    }

    public void setNegativeOpinionCount(int negativeOpinionCount) {
        this.negativeOpinionCount = negativeOpinionCount;
    }

    public int getPositiveOpinionCount() {
        return positiveOpinionCount;
    }

    public void setPositiveOpinionCount(int positiveOpinionCount) {
        this.positiveOpinionCount = positiveOpinionCount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
