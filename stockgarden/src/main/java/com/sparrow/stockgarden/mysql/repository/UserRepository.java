package com.sparrow.stockgarden.mysql.repository;

import com.sparrow.stockgarden.mysql.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 * date: 2018/11/06 17:27
 * description:
 */
public interface UserRepository extends CrudRepository<User,Long> {

}
