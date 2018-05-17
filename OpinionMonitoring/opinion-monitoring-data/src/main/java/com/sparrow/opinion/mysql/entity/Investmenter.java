package com.sparrow.opinion.mysql.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: Investmenter</p>
 * <p>Description: 公司法人集合 entity</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
@Entity
@Table(name = "t_investmenter")
public class Investmenter implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9201034849892179274L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "enterprise_id", nullable = false)
    private Long enterpriseId;//企业id

    @Column(name = "investmenter", nullable = false)
    private String investmenter;//股东名称

    @Column(name = "investmenter_type", nullable = false)
    private String investmenterType;//股东类型

    @Column(name = "created_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate; // 创建时间

    @Column(name = "created_user", nullable = false)
    private String createdUser; // 创建人

    @Column(name = "modified_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedDate; // 更新时间

    @Column(name = "modified_user", nullable = false)
    private String modifiedUser; // 更新人

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
