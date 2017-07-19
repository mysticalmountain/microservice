package com.andx.micro.user.service;

import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PostSampleService;
import com.andx.micro.user.BaseTest;
import com.andx.micro.user.dto.OrgDto;
import com.andx.micro.user.model.Org;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by andongxu on 17-6-30.
 */
public class NewOrgsTest extends BaseTest {

    @Autowired
    @Qualifier("newOrgs")
    private PostSampleService<Request<OrgDto>, Response> newOrgs;

    @Test
    public void parentNotExists() throws ServiceException {
        OrgDto orgDto = new OrgDto();
        orgDto.setPid(Long.valueOf(1001));
        Request<OrgDto> request = new Request<>();
        request.setData(orgDto);
        newOrgs.process(request, null);
    }

    @Test
    public void success() throws ServiceException {
        OrgDto orgDto = new OrgDto();
        orgDto.setName("运维中心");
        orgDto.setPid(Long.valueOf(10000001));
        Request<OrgDto> request = new Request<>();
        request.setData(orgDto);
        Response response = newOrgs.process(request, null);
        Assert.assertTrue(response.getSuccess());
    }
}
