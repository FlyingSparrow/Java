package com.huishu.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wangjianchun
 */
@Entity
@Table(name = "weixintopic")
public class Wechat implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5766716275553544405L;

    @Id
    @Column(name = "Id", unique = true, nullable = false)
    private Long id;

    @Column(name = "Urls")
    private String urls;

    @Column(name = "Title")
    private String title;

    @Lob
    @Column(name = "Content")
    private String content;

    @Column(name = "PostTime")
    private Date postTime;

    @Column(name = "CreateTime")
    private Date createTime;

    @Column(name = "Author")
    private String author;

    //观看
    @Column(name = "ViewNum")
    private String viewNum;

    //点赞
    @Column(name = "LikeNum")
    private String likeNum;

    @Column(name = "IsRead")
    private String isRead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getViewNum() {
        return viewNum;
    }

    public void setViewNum(String viewNum) {
        this.viewNum = viewNum;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
