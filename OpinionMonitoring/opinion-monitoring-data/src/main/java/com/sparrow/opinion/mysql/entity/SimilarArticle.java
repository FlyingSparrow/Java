package com.sparrow.opinion.mysql.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: SimilarArticle</p>
 * <p>Description: 相似文章信息表entity</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
@Entity
@Table(name = "t_similar_article")
public class SimilarArticle implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9201034849892179274L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "article_id")
    private String articleId;// 文章ID

    @Column(name = "article_title")
    private String articleTitle;// 文章标题

    @Column(name = "title")
    private String title;// 相似文章标题

    @Column(name = "similar_article_id")
    private String similarArticleId;    //相似文章id

    @Column(name = "url")
    private String url;// 相似文章url

    @Column(name = "media")
    private String media;// 媒体名称

    @Column(name = "publish_datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDateTime;// 发布日期时间

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

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSimilarArticleId() {
        return similarArticleId;
    }

    public void setSimilarArticleId(String similarArticleId) {
        this.similarArticleId = similarArticleId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public Date getPublishDateTime() {
        return publishDateTime;
    }

    public void setPublishDateTime(Date publishDateTime) {
        this.publishDateTime = publishDateTime;
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
