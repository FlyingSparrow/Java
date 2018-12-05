package com.sparrow.stockgarden.mysql.service;

import com.sparrow.stockgarden.mysql.model.Role;

import java.util.Set;

/**
 * <p>Title: RoleService</p>
 * <p>Description: </p>
 *
 * @author wjc
 * @date 2018/12/5
 */
public interface RoleService {

    Set<Role> getUserRoleList(Long id);
}
