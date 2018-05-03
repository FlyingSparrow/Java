package com.manning.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author wangjianchun
 * @create 2018/4/27
 */
@Entity
public class Reader /*implements UserDetails*/ {

    @Id
    private String username;
    private String fullname;
    private String password;

}
