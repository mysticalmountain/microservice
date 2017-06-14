package com.andx.micro.user.repository;

import com.andx.micro.user.BaseTest;
import com.andx.micro.user.model.User;
import com.andx.micro.user.model.enums.UserType;
import org.junit.Test;

/**
 * Created by andongxu on 17-6-14.
 */
public class UserRepositoryTest extends BaseTest {

    @Test
    public void initUser() {
        User user = new User();
        user.setUserType(UserType.PERSONAL);
        user.setUsername("apple");
        user.setName("苹果");
//        user.set
    }
}
