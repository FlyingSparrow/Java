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
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    /**
     * 密码
     */
    @Column(name = "c_password", nullable = false)
    private String password;
    /**
     * 电子邮箱
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    /**
     * 个人头像
     */
    @Column(name = "profile_picture")
    private String profilePicture;
    /**
     * 背景图片
     */
    @Column(name = "background_picture")
    private String backgroundPicture;
    /**
     * 个人介绍
     */
    @Column(name = "introduction")
    private String introduction;
    /**
     * 过期时间
     */
    @Column(name = "out_date")
    private Date outDate;
    /**
     * 状态（1:正常,2:冻结,3:删除）
     */
    @Column(name = "status", nullable = false)
    private Integer status;
    /**
     * 密码盐
     */
    @Column(name = "salt")
    private String salt;
    /**
     * 验证码
     */
    @Column(name = "verification_code")
    private String verificationCode;
    /**
     * 创建时间
     */
    @Column(name = "created_date", nullable = false)
    private Date createdDate;
    /**
     * 修改时间
     */
    @Column(name = "modified_date", nullable = false)
    private Date modifiedDate;
    @Transient
    private Set<Role> roles;

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}