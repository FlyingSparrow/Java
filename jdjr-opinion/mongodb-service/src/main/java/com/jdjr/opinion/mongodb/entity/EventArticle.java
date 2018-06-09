package com.jdjr.opinion.mongodb.entity;

import com.jdjr.opinion.base.bean.BasePageRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * <p>Title: EventArticle</p>
 * <p>Description: 事件文章信息表entity</p>
 *
 * @author zhangtong
 * @date 2017年6月26日
 */
@Document(collection = "event_article")
public class EventArticle extends BasePageRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 9201034849892179274L;
    @Id
    @Indexed(unique = true)
    private String id;

    @Field("enterprise_id")
    private String enterpriseIdList;// 企业id

    @Field("title")
    private String title;// 标题

    @Field("publish_date")
    private String publishDate;// 发布时间

    @Field("publish_datetime")
    private Date publishDatetime;    //发布日期时间

    @Field("emotion")
    private String emotion;// 情感

    @Field("media")
    private String media;// 媒体名称

    @Field("media_type")
    private String mediaType;    //媒体类型

    @Field("article_type")
    private String articleType;// 文章分类

    /*
     * upd
     */
    @Field("target_article_id")
    private String targetArticleId;// 指向的文章的文章指纹

    @Field("target_article_distance")
    private String targetArticleDistance;// 指向的文章的相似距离

    /*
     * xpf
     * upd
     */
    @Field("own_article_id_list")
    private Set<String> ownArticleIdList;// 指向本文档的文档集合

    @Field("article_id")
    private String articleId;// 文章id

    @Field("keywords")
    private String keywords;    //关键词

    @Field("publish_date2")
    private Date publishDate2;// 发布时间，时间精确到年月日，用于排序

    @Field("hot")
    private Integer hot;    //热度

    @Transient
    private Date startPublishDate;    //发布时间范围（开始）

    @Transient
    private Date endPublishDate;        //发布时间范围（结束）

    @Transient
    private String sortParam;    //需要排序的字段

    @Transient
    private String sortType;    //排序类型

    @Transient
    private String eventId;//事件id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnterpriseIdList() {
        return enterpriseIdList;
    }

    public void setEnterpriseIdList(String enterpriseIdList) {
        this.enterpriseIdList = enterpriseIdList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getTargetArticleId() {
        return targetArticleId;
    }

    public void setTargetArticleId(String targetArticleId) {
        this.targetArticleId = targetArticleId;
    }

    public String getTargetArticleDistance() {
        return targetArticleDistance;
    }

    public void setTargetArticleDistance(String targetArticleDistance) {
        this.targetArticleDistance = targetArticleDistance;
    }

    public Set<String> getOwnArticleIdList() {
        return ownArticleIdList;
    }

    public void setOwnArticleIdList(Set<String> ownArticleIdList) {
        this.ownArticleIdList = ownArticleIdList;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Date getPublishDate2() {
        return publishDate2;
    }

    public void setPublishDate2(Date publishDate2) {
        this.publishDate2 = publishDate2;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
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

    public String getSortParam() {
        return sortParam;
    }

    public void setSortParam(String sortParam) {
        this.sortParam = sortParam;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
