package com.andx.micro.user.service;

import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.user.BaseTest;
import com.andx.micro.user.dto.OrgDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by andongxu on 17-6-30.
 */
public class QueryListOrgsTest extends BaseTest {

    @Autowired
    @Qualifier("queryTreeOrgs")
    private GetSampleService<Response<List<OrgDto>>> queryTreeOrgs;

    @Test
    public void orgNotExists() {
        try {
            queryTreeOrgs.process(null, new String [] {null, "/list-orgs/1001"});
        } catch (ServiceException e) {
            Assert.assertTrue(e instanceof ServiceException);
        }
    }

    @Test
    public void orgIsRoot() throws ServiceException {
        Response<List<OrgDto>> orgDtos = queryTreeOrgs.process(null, new String [] {null, "/tree-orgs/10000000"});
        Assert.assertNotNull(orgDtos);

    }
}
