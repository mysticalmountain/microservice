package com.andx.micro.user.repository;

import com.andx.micro.user.model.Rolebak;
import com.andx.micro.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andongxu on 16-11-14.
 */
@Repository
public interface RolebakRepository extends JpaRepository<Rolebak, Long> {

    public void deleteByUsers(User user);

}
