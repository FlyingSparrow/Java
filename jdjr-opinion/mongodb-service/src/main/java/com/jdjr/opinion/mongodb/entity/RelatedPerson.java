package com.jdjr.opinion.mongodb.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * <p>Title: RelatedPerson</p>
 * <p>Description: 相关人物信息表entity</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
@Document(collection = "related_person")
public class RelatedPerson implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9201034849892179274L;
    @Id
    @Indexed(unique = true)
    private String id;

    @Field("enterprise_id")
    private String enterpriseId;    // 企业id

    @Field("name")
    private String name;    // 相关人物姓名

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
