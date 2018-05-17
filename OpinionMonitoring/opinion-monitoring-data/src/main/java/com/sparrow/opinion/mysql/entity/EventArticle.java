package com.sparrow.opinion.mysql.entity;

import com.sparrow.opinion.base.bean.BasePageRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: EventArticle</p>
 * <p>Description: 事件文章信息表entity</p>
 *
 * @author zhangtong
 * @date 2017年6月26日
 */
@Entity
@Table(name = "t_event_article")
public class EventArticle extends BasePageRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 9201034849892179274L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "enterprise_id", nullable = false)
    private Long enterpriseIdList;// 企业id

    @Column(name = "title", nullable = false)
    private String title;// 标题

    @Column(name = "publish_date", nullable = false)
    private String publishDate;// 发布时间

    @Column(name = "publish_datetime", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDatetime;    //发布日期时间

    @Column(name = "emotion", nullable = false)
    private String emotion;// 情感

    @Column(name = "media")
    private String media;// 媒体名称

    @Column(name = "media_type")
    private String mediaType;    //媒体类型

    @Column(name = "article_type")
    private String articleType;// 文章分类

    /*
     * upd
     */
    @Column(name = "target_article_id")
    private String targetArticleId;// 指向的文章的文章指纹

    @Column(name = "target_article_distance")
    private String targetArticleDistance;// 指向的文章的相似距离

    /*
     * xpf
     * upd
     */
    @Column(name = "own_article_id_list")
    private String ownArticleIdList;// 指向本文档的文档集合

    @Column(name = "article_id")
    private String articleId;// 文章id

    @Column(name = "keywords")
    private String keywords;    //关键词

    @Column(name = "publish_date2")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate2;// 发布时间，时间精确到年月日，用于排序

    @Column(name = "hot")
    private Integer hot;    //热度

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
    private Date endPublishDate;        //发布时间范围（结束）

    @Transient
    private String sortParam;    //需要排序的字段

    @Transient
    private String sortType;    //排序类型

    @Transient
    private Long eventId;//事件id

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseIdList() {
        return enterpriseIdList;
    }

    public void setEnterpriseIdList(Long enterpriseIdList) {
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

    public String getOwnArticleIdList() {
        return ownArticleIdList;
    }

    public void setOwnArticleIdList(String ownArticleIdList) {
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

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
