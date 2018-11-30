package com.sparrow.stockgarden.mysql.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 *         date: 2018/11/06 17:27
 *         description:
 */
@Entity
@Table(name = "t_briefing_customized_folder")
public class BriefingCustomizedFolder {
    /**
     *
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 文件夹名称
     */
    private String folderName;
    /**
     * 1 : 日报2：周报3：月报4：季报5：年报6：文件管理
     */
    private Integer folderType;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 删除状态：1已删除 0未删除
     */
    private Integer deleteState;
    /**
     * 线索id 关联l_clues_sys表的id
     */
    private Long clueId;
    /**
     * 创建人id
     */
    private Long creatorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public Integer getFolderType() {
        return folderType;
    }

    public void setFolderType(Integer folderType) {
        this.folderType = folderType;
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

    public Long getClueId() {
        return clueId;
    }

    public void setClueId(Long clueId) {
        this.clueId = clueId;
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
