package com.jdjr.opinion.mongodb.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: OpinionRiskEval</p>
 * <p>Description: 舆情风险表entity</p>
 *
 * @author zhangtong
 * @date 2017年6月26日
 */
@Document(collection = "opinion_risk")
public class OpinionRisk implements Serializable {

    private static final long serialVersionUID = 9201034849892179274L;

    @Id
    @Indexed(unique = true)
    private String id;

    @Field("enterprise_name")
    private String enterpriseName; // 企业名称

    @Field("enterprise_id")
    private String enterpriseId; // 企业id

    @Field("publish_date")
    private String publishDate; // 发布时间（yyyy-MM-dd）

    @Field("publish_datetime")
    private Date publishDatetime;    //发布日期时间

    @Field("pressure_index")
    private Double pressureIndex; // 整体压力指数，今天的压力指数

    @Field("transformed_pressure_index")
    private Double transformedPressureIndex; // 整体压力指数，今天的压力指数

    @Field("whole_standard_diviation")
    private Double wholeStandardDiviation;  //整体压力（标准差）

    @Field("whole_average_value")
    private Double wholeAverageValue;   //整体压力（均值）

    @Field("total_opinion")
    private Integer totalOpinion; // 舆情总数(当日的舆情总数)

    @Field("positive_opinion_count")
    private Integer positiveOpinionCount; // 正面舆情数(当日的正面舆情数)

    @Field("negative_opinion_count")
    private Integer negativeOpinionCount; // 负面舆情数(当日的负面舆情数)

    @Field("positive_pressure_index")
    private Double positivePressureIndex; // 正面压力(正面压力指数)

    @Field("transformed_positive_pressure_index")
    private Double transformedPositivePressureIndex; // 正面压力(正面压力指数)

    @Field("positive_standard_diviation")
    private Double positiveStandardDiviation;   //正面压力（标准差）

    @Field("positive_average_value")
    private Double positiveAverageValue;    //正面压力（均值）

    @Field("negative_pressure_index")
    private Double negativePressureIndex; // 负面压力(负面压力指数)

    @Field("transformed_negative_pressure_index")
    private Double transformedNegativePressureIndex; // 负面压力(负面压力指数)

    @Field("negative_standard_diviation")
    private Double negativeStandardDiviation;   //负面压力（标准差）

    @Field("negative_average_value")
    private Double negativeAverageValue;   //负面压力（均值）
    
    @Field("is_warning")
    private String isWarning = "0";  //是否预警分析，0表示未预警分析，1表示已预警分析

    @Transient
    private String pressureType;    //压力类型（整体压力；正面压力；负面压力）

    @Transient
    private Date startPublishDate;    //发布时间范围（开始）

    @Transient
    private Date endPublishDate;        //发布时间范围（结束）

    @Transient
    private Integer limit;  //获取个数

    @Transient
    private Boolean isContain;  //日期是否包含
    
    public String getIsWarning() {
		return isWarning;
	}

	public void setIsWarning(String isWarning) {
		this.isWarning = isWarning;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Date getPublishDatetime() {
        return publishDatetime;
    }

    public void setPublishDatetime(Date publishDatetime) {
        this.publishDatetime = publishDatetime;
    }

    public Double getPressureIndex() {
        return pressureIndex;
    }

    public void setPressureIndex(Double pressureIndex) {
        this.pressureIndex = pressureIndex;
    }

    public Double getTransformedPressureIndex() {
        return transformedPressureIndex;
    }

    public void setTransformedPressureIndex(Double transformedPressureIndex) {
        this.transformedPressureIndex = transformedPressureIndex;
    }

    public Double getWholeStandardDiviation() {
        return wholeStandardDiviation;
    }

    public void setWholeStandardDiviation(Double wholeStandardDiviation) {
        this.wholeStandardDiviation = wholeStandardDiviation;
    }

    public Double getWholeAverageValue() {
        return wholeAverageValue;
    }

    public void setWholeAverageValue(Double wholeAverageValue) {
        this.wholeAverageValue = wholeAverageValue;
    }

    public Integer getTotalOpinion() {
        return totalOpinion;
    }

    public void setTotalOpinion(Integer totalOpinion) {
        this.totalOpinion = totalOpinion;
    }

    public Integer getPositiveOpinionCount() {
        return positiveOpinionCount;
    }

    public void setPositiveOpinionCount(Integer positiveOpinionCount) {
        this.positiveOpinionCount = positiveOpinionCount;
    }

    public Integer getNegativeOpinionCount() {
        return negativeOpinionCount;
    }

    public void setNegativeOpinionCount(Integer negativeOpinionCount) {
        this.negativeOpinionCount = negativeOpinionCount;
    }

    public Double getPositivePressureIndex() {
        return positivePressureIndex;
    }

    public void setPositivePressureIndex(Double positivePressureIndex) {
        this.positivePressureIndex = positivePressureIndex;
    }

    public Double getTransformedPositivePressureIndex() {
        return transformedPositivePressureIndex;
    }

    public void setTransformedPositivePressureIndex(Double transformedPositivePressureIndex) {
        this.transformedPositivePressureIndex = transformedPositivePressureIndex;
    }

    public Double getPositiveStandardDiviation() {
        return positiveStandardDiviation;
    }

    public void setPositiveStandardDiviation(Double positiveStandardDiviation) {
        this.positiveStandardDiviation = positiveStandardDiviation;
    }

    public Double getPositiveAverageValue() {
        return positiveAverageValue;
    }

    public void setPositiveAverageValue(Double positiveAverageValue) {
        this.positiveAverageValue = positiveAverageValue;
    }

    public Double getNegativePressureIndex() {
        return negativePressureIndex;
    }

    public void setNegativePressureIndex(Double negativePressureIndex) {
        this.negativePressureIndex = negativePressureIndex;
    }

    public Double getTransformedNegativePressureIndex() {
        return transformedNegativePressureIndex;
    }

    public void setTransformedNegativePressureIndex(Double transformedNegativePressureIndex) {
        this.transformedNegativePressureIndex = transformedNegativePressureIndex;
    }

    public Double getNegativeStandardDiviation() {
        return negativeStandardDiviation;
    }

    public void setNegativeStandardDiviation(Double negativeStandardDiviation) {
        this.negativeStandardDiviation = negativeStandardDiviation;
    }

    public Double getNegativeAverageValue() {
        return negativeAverageValue;
    }

    public void setNegativeAverageValue(Double negativeAverageValue) {
        this.negativeAverageValue = negativeAverageValue;
    }

    public String getPressureType() {
        return pressureType;
    }

    public void setPressureType(String pressureType) {
        this.pressureType = pressureType;
    }

    public Date getStartPublishDate() {
        return startPublishDate;
    }

    public void setStartPublishDate(Date startPublishDate) {
        this.startPublishDate = startPublishDate;
    }

    public Date getEndPublishDate() {
        return endPublishDate;
    }

    public void setEndPublishDate(Date endPublishDate) {
        this.endPublishDate = endPublishDate;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Boolean getContain() {
        return isContain;
    }

    public void setContain(Boolean contain) {
        isContain = contain;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
