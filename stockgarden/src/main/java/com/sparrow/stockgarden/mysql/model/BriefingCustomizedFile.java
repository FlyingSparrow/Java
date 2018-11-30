package com.sparrow.stockgarden.mysql.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 *         date: 2018/11/06 17:27
 *         description:
 */
@Entity
@Table(name = "t_briefing_customized_file")
public class BriefingCustomizedFile {
    /**
     *
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 文件夹id
     */
    private Long folderId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 报告时间
     */
    private Date briefingDate;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件大小，单位：M
     */
    private Double fileSize;
    /**
     * 文件md5
     */
    private String fileMd5;
    /**
     * 存在服务器的名称
     */
    private String fullFileName;
    /**
     * 原名称 带后缀
     */
    private String originalFileName;
    /**
     * 原名称
     */
    private String fileName;
    /**
     * 后缀名
     */
    private String suffixName;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 删除状态：1已删除 0未删除
     */
    private Integer deleteState;
    /**
     * 创建人id
     */
    private Long creatorId;
    @Transient
    private String creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getBriefingDate() {
        return briefingDate;
    }

    public void setBriefingDate(Date briefingDate) {
        this.briefingDate = briefingDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public String getFullFileName() {
        return fullFileName;
    }

    public void setFullFileName(String fullFileName) {
        this.fullFileName = fullFileName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Integer deleteState) {
        this.deleteState = deleteState;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
