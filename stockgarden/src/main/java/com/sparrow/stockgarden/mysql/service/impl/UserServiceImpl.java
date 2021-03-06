package com.sparrow.stockgarden.mysql.service.impl;

import com.sparrow.stockgarden.mysql.model.User;
import com.sparrow.stockgarden.mysql.repository.UserRepository;
import com.sparrow.stockgarden.mysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Title: UserServiceImpl</p>
 * <p>Description: </p>
 *
 * @author wjc
 * @date 2018/12/5
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User getByName(String username) {
        return null;
    }

    @Override
    public User findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean save(User entity) {
        userRepository.save(entity);
        return true;
    }

    @Override
    public boolean setNewPassword(String password, String email) {
        userRepository.setNewPassword(password, email);
        return true;
    }

    @Override
    public boolean setProfilePicture(String profilePicture, Long id) {
        userRepository.setProfilePicture(profilePicture, id);
        return true;
    }

    @Override
    public boolean setUsername(String username, String email) {
        userRepository.setUsername(username, email);
        return true;
    }

    @Override
    public boolean setIntroduction(String introduction, String email) {
        userRepository.setIntroduction(introduction, email);
        return true;
    }
}
