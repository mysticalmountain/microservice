package com.andx.micro.user.repository;

import com.andx.micro.user.model.Authority;
import com.andx.micro.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andongxu on 16-11-16.
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    public Authority findByAccountNo(String accountNo);

    public void deleteByUser(User user);

}
