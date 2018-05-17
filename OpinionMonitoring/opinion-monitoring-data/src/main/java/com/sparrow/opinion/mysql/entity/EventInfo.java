package com.sparrow.opinion.mysql.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: EventInfo</p>
 * <p>Description: 事件信息表entity</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
@Entity
@Table(name = "t_event_info")
public class EventInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9201034849892179274L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "event_name")
    private String eventName;//事件名称(代表文档的标题)

    @Column(name = "enterprise_name")
    private String enterpriseName;    //企业名称(来自于谢鹏飞的算法包)

    @Column(name = "enterprise_id")
    private Long enterpriseId;    //企业ID

    @Column(name = "article_id_list")
    private String articleIdList;    //事件文章id集合

    @Column(name = "event_type")
    private String eventType;// 事件分类(业务部门提供)

    @Column(name = "core_article_id")
    private String coreArticleId;    //核心文章id

    @Column(name = "core_article_title")
    private String coreArticleTitle; //核心文章标题

    @Column(name = "publish_datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDatetime;    //发布日期时间

    @Column(name = "is_replace")
    private String isReplace;    //是否被替换

    @Column(name = "replace_id")
    private Long replaceId;    //被该事件包含或取代

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

    @Transient
    private Date startPublishDate;    //发布时间范围（开始）

    @Transient
    private Date endPublishDate; //发布时间范围（结束）

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getArticleIdList() {
        return articleIdList;
    }

    public void setArticleIdList(String articleIdList) {
        this.articleIdList = articleIdList;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getCoreArticleId() {
        return coreArticleId;
    }

    public void setCoreArticleId(String coreArticleId) {
        this.coreArticleId = coreArticleId;
    }

    public String getCoreArticleTitle() {
        return coreArticleTitle;
    }

    public void setCoreArticleTitle(String coreArticleTitle) {
        this.coreArticleTitle = coreArticleTitle;
    }

    public Date getPublishDatetime() {
        return publishDatetime;
    }

    public void setPublishDatetime(Date publishDatetime) {
        this.publishDatetime = publishDatetime;
    }

    public String getIsReplace() {
        return isReplace;
    }

    public void setIsReplace(String isReplace) {
        this.isReplace = isReplace;
    }

    public Long getReplaceId() {
        return replaceId;
    }

    public void setReplaceId(Long replaceId) {
        this.replaceId = replaceId;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
