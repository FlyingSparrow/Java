package com.jdjr.opinion.mongodb.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * <p>Title: RelatedEnterprise</p>
 * <p>Description: 相关企业信息表entity</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
@Document(collection = "related_enterprise")
public class RelatedEnterprise implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9201034849892179274L;
    @Id
    @Indexed(unique = true)
    private String id;

    @Field("enterprise_id")
    private String enterpriseId;    // 企业id

    @Field("related_enterprise_id")
    private String relatedEnterpriseId;    //相关企业id

    @Field("enterprise_name")
    private String enterpriseName;    // 相关企业名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getRelatedEnterpriseId() {
        return relatedEnterpriseId;
    }

    public void setRelatedEnterpriseId(String relatedEnterpriseId) {
        this.relatedEnterpriseId = relatedEnterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
