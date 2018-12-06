package com.sparrow.stockgarden.mysql.service;

import com.sparrow.stockgarden.mysql.model.User;

/**
 * <p>Title: UserService</p>
 * <p>Description: </p>
 *
 * @author wjc
 * @date 2018/12/5
 */
public interface UserService {

    User findById(Long id);

    User getByName(String username);

    User findByUsernameOrEmail(String username, String email);
}
