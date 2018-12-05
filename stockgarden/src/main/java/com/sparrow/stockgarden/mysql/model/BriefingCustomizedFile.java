package com.sparrow.stockgarden.mysql.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 *         date: 2018/11/06 17:27
 *         description:
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "t_briefing_customized_file")
public class BriefingCustomizedFile extends BaseEntity {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 删除状态：1已删除 0未删除
     */
    private Integer deleteState;
    /**
     * 创建人id
     */
    private Long creatorId;
    /**
     * 创建时间
     */
    @Column(nullable = false)
    private Date createdDate;
    /**
     * 修改时间
     */
    @Column(nullable = false)
    private Date modifiedDate;
    @Transient
    private String creator;
}
