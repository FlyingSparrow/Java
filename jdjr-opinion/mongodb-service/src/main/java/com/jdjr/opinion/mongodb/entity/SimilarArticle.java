package com.jdjr.opinion.mongodb.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: SimilarArticle</p>
 * <p>Description: 相似文章信息表entity</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
@Document(collection = "similar_article")
public class SimilarArticle implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9201034849892179274L;

    @Id
    @Indexed(unique = true)
    private String id;

    @Field("article_id")
    private String articleId;// 文章ID

    @Field("article_title")
    private String articleTitle;// 文章标题

    @Field("title")
    private String title;// 相似文章标题

    @Field("similar_article_id")
    private String similarArticleId;    //相似文章id

    @Field("url")
    private String url;// 相似文章url

    @Field("media")
    private String media;// 媒体名称

    @Field("publish_datetime")
    private Date publishDateTime;// 发布日期时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
