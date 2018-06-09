package com.jdjr.opinion.mongodb.entity;

import com.jdjr.opinion.base.bean.BasePageRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: OpinionDetailsSnapshot</p>
 * <p>Description: 舆情明细快照表entity</p>
 *
 * @author zhangtong
 * @date 2017年6月23日
 */
@Document(collection = "opinion_details_snapshot")
public class OpinionDetailsSnapshot extends BasePageRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9201034849892179274L;
    @Id
    @Indexed(unique = true)
    private String id;

    @Field("enterprise_name")
    private String enterpriseName;// 企业名称

    @Field("enterprise_id")
    private String enterpriseId;// 企业ID

    @Field("title")
    private String title;// 文档标题

    @Field("url")
    private String url;// url

    @Field("media")
    private String media;// 媒体名称

    @Field("media_type")
    private String mediaType;// 媒体类型

    @Field("publish_date")
    private String publishDate;// 发布时间

    @Field("publish_datetime")
    private Date publishDatetime;// 发布时间

    @Field("emotion")
    private String emotion;// 情感

    @Field("hot")
    private Integer hot;// 热度

    @Field("keywords")
    private String keywords;// 关键词

    @Field("title_article_fingerprint")
    private String titleArticleFingerprint;// 标题的文章指纹

    @Field("content_article_fingerprint")
    private String contentArticleFingerprint;// 内容的文章指纹

    @Field("article_id")
    private String articleId;// 文章id

    @Field("article_type")
    private String articleType;    //文章类型

    @Field("crawler_date")
    private String crawlerDate;// 采集时间，格式：yyyy-MM-dd

    @Field("crawler_datetime")
    private Date crawlerDateTime;// 采集时间

    @Field("judge_type")
    private String judgeType;   //研判类型

    @Field("judge_number")
    private Integer judgeNumber;   //研判次数

    @Field("judger")
    private String judger;  //研判人
    
    @Field("is_warning")
    private String isWarning = "0";  //是否预警分析，0表示未预警分析，1表示已预警分析

    @Field("is_analyzed")
    private String isAnalyzed = "0";  //是否经过事件分析，0表示未分析，1表示已分析

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

    @Transient
    private Integer limit;//显示数量
    
    public String getIsWarning() {
		return isWarning;
	}

	public void setIsWarning(String isWarning) {
		this.isWarning = isWarning;
	}

    public String getIsAnalyzed() {
        return isAnalyzed;
    }

    public void setIsAnalyzed(String isAnalyzed) {
        this.isAnalyzed = isAnalyzed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
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

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTitleArticleFingerprint() {
        return titleArticleFingerprint;
    }

    public void setTitleArticleFingerprint(String titleArticleFingerprint) {
        this.titleArticleFingerprint = titleArticleFingerprint;
    }

    public String getContentArticleFingerprint() {
        return contentArticleFingerprint;
    }

    public void setContentArticleFingerprint(String contentArticleFingerprint) {
        this.contentArticleFingerprint = contentArticleFingerprint;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getCrawlerDate() {
        return crawlerDate;
    }

    public void setCrawlerDate(String crawlerDate) {
        this.crawlerDate = crawlerDate;
    }

    public Date getCrawlerDateTime() {
        return crawlerDateTime;
    }

    public void setCrawlerDateTime(Date crawlerDateTime) {
        this.crawlerDateTime = crawlerDateTime;
    }

    public String getJudgeType() {
        return judgeType;
    }

    public void setJudgeType(String judgeType) {
        this.judgeType = judgeType;
    }

    public Integer getJudgeNumber() {
        return judgeNumber;
    }

    public void setJudgeNumber(Integer judgeNumber) {
        this.judgeNumber = judgeNumber;
    }

    public String getJudger() {
        return judger;
    }

    public void setJudger(String judger) {
        this.judger = judger;
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
