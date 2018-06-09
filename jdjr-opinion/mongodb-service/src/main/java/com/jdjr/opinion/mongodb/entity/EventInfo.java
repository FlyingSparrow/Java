package com.jdjr.opinion.mongodb.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * <p>Title: EventInfo</p>
 * <p>Description: 事件信息表entity</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
@Document(collection = "event_info")
public class EventInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9201034849892179274L;
    @Id
    @Indexed(unique = true)
    private String id;

    @Field("event_name")
    private String eventName;//事件名称(代表文档的标题)

    @Field("enterprise_name")
    private String enterpriseName;    //企业名称(来自于谢鹏飞的算法包)

    @Field("enterprise_id")
    private String enterpriseId;    //企业ID

    @Field("article_id_list")
    private Set<String> articleIdList;    //事件文章id集合

    @Field("event_type")
    private String eventType;// 事件分类(业务部门提供)

    @Field("core_article_id")
    private String coreArticleId;    //核心文章id

    @Field("core_article_title")
    private String coreArticleTitle; //核心文章标题

    @Field("publish_datetime")
    private Date publishDatetime;    //发布日期时间

    @Field("is_replace")
    private String isReplace;    //是否被替换

    @Field("replace_id")
    private String replaceId;    //被该事件包含或取代
    @Transient
    private Date startPublishDate;    //发布时间范围（开始）

    @Transient
    private Date endPublishDate; //发布时间范围（结束）

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getIsReplace() {
        return isReplace;
    }

    public void setIsReplace(String isReplace) {
        this.isReplace = isReplace;
    }

    public Set<String> getArticleIdList() {
        return articleIdList;
    }

    public void setArticleIdList(Set<String> articleIdList) {
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

    public String getReplaceId() {
        return replaceId;
    }

    public void setReplaceId(String replaceId) {
        this.replaceId = replaceId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
