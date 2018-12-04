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
@Table(name = "t_briefing_customized_folder")
public class BriefingCustomizedFolder extends Entitys {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 1: 日报2：周报3：月报4：季报5：年报6：文件管理
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
}
