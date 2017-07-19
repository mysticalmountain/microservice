package com.andx.micro.user.repository;

import com.andx.micro.user.BaseTest;
import com.andx.micro.user.model.Profile;
import com.andx.micro.user.model.User;
import com.andx.micro.user.model.enums.UserType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 17-6-14.
 */
public class UserRepositoryTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void initUser() {
        User user = new User();
        user.setUserType(UserType.PERSONAL);
        user.setUsername("apple");
        user.setName("苹果");
        userRepository.save(user);
        Profile profile = new Profile();
//        user.set
    }
}
