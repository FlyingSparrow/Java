package com.jdjr.opinion.mongodb.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * <p>Title: Investmenter</p>
 * <p>Description: 公司法人集合 entity</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
public class Investmenter implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9201034849892179274L;


    @Field("investmenter")
    private String investmenter;

    @Field("investmenter_type")
    private String investmenterType;

    public String getInvestmenter() {
        return investmenter;
    }

    public void setInvestmenter(String investmenter) {
        this.investmenter = investmenter;
    }

    public String getInvestmenterType() {
        return investmenterType;
    }

    public void setInvestmenterType(String investmenterType) {
        this.investmenterType = investmenterType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
