package com.sparrow.stockgarden.mysql.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * <p>Title: User</p>
 * <p>Description: </p>
 *
 * @author wjc
 * @date 2018/12/5
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "t_user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 用户名
     */
    @Column(nullable = false, unique = true)
    private String username;
    /**
     * 密码
     */
    @Column(nullable = false)
    private String password;
    /**
     * 电子邮箱
     */
    @Column(nullable = false, unique = true)
    private String email;
    /**
     * 个人头像
     */
    @Column
    private String profilePicture;
    /**
     * 背景图片
     */
    @Column
    private String backgroundPicture;
    /**
     * 个人介绍
     */
    @Column
    private String introduction;
    /**
     * 过期时间
     */
    @Column
    private Date outDate;
    /**
     * 状态（1:正常,2:冻结,3:删除）
     */
    @Column(nullable = false)
    private Integer status;
    /**
     * 密码盐
     */
    @Column
    private String salt;
    /**
     * 验证码
     */
    @Column
    private String validataCode;
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
    private Set<Role> roles;

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}