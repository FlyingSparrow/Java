package com.sparrow.stockgarden.mysql.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: Role</p>
 * <p>Description: </p>
 *
 * @author wjc
 * @date 2018/12/5
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="sys_role")
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 角色名称（中文名）
     */
    @Column(nullable = false)
    private String name;
    /**
     * 标识名称
     */
    @Column
    private String title;
    /**
     * 备注
     */
    @Column
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
    @Transient
    private List<Menu> menus;

    public List<Menu> getMenus() {
        return menus;
    }
}
