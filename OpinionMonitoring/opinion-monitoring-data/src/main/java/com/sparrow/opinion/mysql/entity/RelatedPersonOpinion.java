package com.sparrow.opinion.mysql.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: RelatedPersonOpinion</p>
 * <p>Description: 相关人物舆情信息表entity</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
@Entity
@Table(name = "t_related_person_opinion")
public class RelatedPersonOpinion implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 9201034849892179274L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "related_person_name")
    private String relatedPersonName;// 相关人物名称

    @Column(name = "enterprise_id")
    private Long enterpriseId;// 企业id

    @Column(name = "title")
    private String title;// 标题

    @Column(name = "media")
    private String media;// 媒体名称

    @Column(name = "publish_date")
    private String publishDate;// 发布时间

    @Column(name = "publish_datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDatetime;    //发布日期时间

    @Column(name = "url")
    private String url;    //url

    @Column(name = "article_id")
    private String articleId;    //文章id

    @Column(name = "emotion")
    private String emotion;    //情感

    @Column(name = "hot")
    private Integer hot;// 热度

    @Column(name = "article_type")
    private String articleType;    //文章类型

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
    private Integer limit;    //显示个数

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRelatedPersonName() {
        return relatedPersonName;
    }

    public void setRelatedPersonName(String relatedPersonName) {
        this.relatedPersonName = relatedPersonName;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
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

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
