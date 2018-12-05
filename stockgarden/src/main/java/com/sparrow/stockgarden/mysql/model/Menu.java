package com.sparrow.stockgarden.mysql.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Title: Menu</p>
 * <p>Description: </p>
 *
 * @author wjc
 * @date 2018/12/5
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="sys_menu")
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 菜单名称
     */
    @Column(nullable = false)
    private String title;
    /**
     * 父级编号
     */
    @Column(nullable = false)
    private Long pid;
    /**
     * 所有父级编号
     */
    @Column(nullable = false)
    private String pids;
    /**
     * URL地址
     */
    @Column(nullable = false)
    private String url;
    /**
     * 图标
     */
    @Column(nullable = false)
    private String icon;
    /**
     * 类型（1:一级菜单,2:子级菜单,3:不是菜单）
     */
    @Column(nullable = false)
    private Integer type;
    /**
     * 排序
     */
    @Column(nullable = false)
    private Integer sort;
    /**
     * 备注
     */
    @Column(nullable = false)
    private String remark;
    /**
     * 状态（1:正常,2:冻结,3:删除）
     */
    @Column(nullable = false)
    private Integer status;
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
