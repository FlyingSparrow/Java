package com.jdjr.opinion.mongodb.entity;

import com.forget.test.SubModelColl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "product")
public class Product implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @Indexed(unique = true)
    private String id;
    @Field("enterprise_id")
    private String enterpriseId;
    @Field("product")
    private Map<String, SubModelColl> product;

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Map<String, SubModelColl> getProduct() {
        return product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProduct(Map<String, SubModelColl> product) {
        this.product = product;
    }

}
