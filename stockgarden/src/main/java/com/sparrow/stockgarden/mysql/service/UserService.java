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

    User findByEmail(String email);

    User findByUsername(String username);

    boolean save(User entity);

    boolean setNewPassword(String password, String email);

    boolean setProfilePicture(String profilePicture, Long id);

    boolean setUsername(String username, String email);

    boolean setIntroduction(String introduction, String email);
}
