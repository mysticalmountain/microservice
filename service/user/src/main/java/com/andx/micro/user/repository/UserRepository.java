package com.andx.micro.user.repository;

import com.andx.micro.user.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andongxu on 16-6-6.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsername(String username) throws DataAccessException;

}
