package com.andx.micro.user.service;

import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.SampleService;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.DeleteSampleService;
import com.andx.micro.user.BaseTest;
import com.netflix.discovery.converters.Auto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by andongxu on 17-6-30.
 */
public class DeleteOrgsTest extends BaseTest {

    @Autowired
    @Qualifier("deleteOrgs")
    private DeleteSampleService<Response> deleteOrgs;

    @Test
    public void deleteOne() throws ServiceException {
        Response response = deleteOrgs.process(null, new String [] {null, "/orgs/10000001,10000002,10000003"});
        Assert.assertNotNull(response);
    }
}
