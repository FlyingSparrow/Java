package com.sparrow.stockgarden.mysql.service;

import com.sparrow.stockgarden.mysql.model.User;
import org.springframework.data.repository.query.Param;

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

    User findByEmail(String email);

    User findByUsername(String username);

    boolean save(User entity);

    boolean setNewPassword(@Param("password") String password, @Param("email") String email);
}
