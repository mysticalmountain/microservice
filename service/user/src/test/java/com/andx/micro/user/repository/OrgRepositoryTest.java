package com.andx.micro.user.repository;

import com.andx.micro.user.BaseTest;
import com.andx.micro.user.model.Org;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 17-6-30.
 */
public class OrgRepositoryTest extends BaseTest {

    @Autowired
    private OrgRepository orgRepository;

    @Test
    public void initRootOrg() {
        Org root = new Org();
        root.setName("root");
        orgRepository.save(root);
    }


}
