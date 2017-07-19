package com.andx.micro.user.service;

import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.user.BaseTest;
import com.andx.micro.user.dto.OrgDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 17-6-30.
 */
public class QueryTreeOrgsTest extends BaseTest {

    @Autowired
    private GetSampleService<Response<OrgDto>> queryTreeOrgs;

    @Test
    public void success() throws ServiceException {
        Response response = queryTreeOrgs.process(null, new String [] {null, "/tree-orgs/10000000"});
        Assert.assertNotNull(response);
    }
}
