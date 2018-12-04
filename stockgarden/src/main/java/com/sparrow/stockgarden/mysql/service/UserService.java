package com.sparrow.stockgarden.mysql.service;

import com.sparrow.stockgarden.mysql.model.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 * date: 2018/11/06 17:27
 * description:
 */
public interface UserService {

    User findById(Long id);

}
