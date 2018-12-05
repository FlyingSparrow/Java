package com.sparrow.stockgarden.mysql.service.impl;

import com.sparrow.stockgarden.mysql.model.Role;
import com.sparrow.stockgarden.mysql.repository.RoleRepository;
import com.sparrow.stockgarden.mysql.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * <p>Title: RoleServiceImpl</p>
 * <p>Description: </p>
 *
 * @author wjc
 * @date 2018/12/5
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Set<Role> getUserRoleList(Long id) {
        return null;
    }
}
