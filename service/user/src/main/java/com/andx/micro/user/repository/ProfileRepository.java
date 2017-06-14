package com.andx.micro.user.repository;

import com.andx.micro.user.model.Profile;
import com.andx.micro.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andongxu on 16-11-16.
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

//    @Query("delete from Profile where user_id = ?1")
//    public void deleteByUserId(Long userId);

    public void deleteByUser(User user);
}
