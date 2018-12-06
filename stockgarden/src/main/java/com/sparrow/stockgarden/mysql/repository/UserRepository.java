package com.sparrow.stockgarden.mysql.repository;

import com.sparrow.stockgarden.mysql.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * <p>Title: UserRepository</p>
 * <p>Description: </p>
 *
 * @author wjc
 * @date 2018/12/5
 */
public interface UserRepository extends CrudRepository<User,Long> {

    User findByUsernameOrEmail(String username, String email);

    User findByEmail(String email);

    User findByUsername(String username);

}
