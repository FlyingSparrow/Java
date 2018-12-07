package com.sparrow.stockgarden.mysql.repository;

import com.sparrow.stockgarden.mysql.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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

    @Modifying(clearAutomatically=true)
    @Query("update User set password=:password where email=:email")
    void setNewPassword(@Param("password") String password, @Param("email") String email);

    @Modifying(clearAutomatically=true)
    @Query("update User set profilePicture=:profilePicture where id=:id")
    void setProfilePicture(@Param("profilePicture") String profilePicture, @Param("id") Long id);

}
