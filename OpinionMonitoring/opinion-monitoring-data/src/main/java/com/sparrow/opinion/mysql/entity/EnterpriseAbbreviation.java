package com.sparrow.opinion.mysql.entity;

import com.sparrow.opinion.base.bean.BasePageRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 企业简称实体类
 * Created by wangjianchun on 2017/12/7.
 */
@Entity
@Table(name = "t_enterprise_abbreviation")
public class EnterpriseAbbreviation extends BasePageRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "enterprise_id", nullable = false)
    private String enterpriseId; // 企业id

    @Column(name = "enterprise_name", nullable = false)
    private String enterpriseName; // 企业名称

    @Column(name = "abbreviation")
    private String abbreviation; // 企业简称

    @Column(name = "exclude_words")
    private String excludeWords;//排除词

    @Column(name = "allow_delete", nullable = false)
    private String allowDelete; // 是否允许删除，系统生成的简称不允许删除，用户添加的简称可以删除

    @Column(name = "delete_flag", nullable = false)
    private String deleteFlag; // 删除标记，已删除：是：未删除：否

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

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getExcludeWords() {
        return excludeWords;
    }

    public void setExcludeWords(String excludeWords) {
        this.excludeWords = excludeWords;
    }

    public String getAllowDelete() {
        return allowDelete;
    }

    public void setAllowDelete(String allowDelete) {
        this.allowDelete = allowDelete;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
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
