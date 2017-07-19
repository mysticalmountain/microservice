package com.andx.micro.user.service.org;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PostSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.dto.OrgDto;
import com.andx.micro.user.model.Org;
import com.andx.micro.user.repository.OrgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-6-30.
 */
@Component
@Service(path = "/org", code = "newOrg", method = MethodType.POST, name = "新建机构", module = "user")
public class NewOrg extends PostSampleService<Request<OrgDto>, Response<Boolean>> {

    @Autowired
    private OrgRepository orgRepository;

    @Override
    protected Response<Boolean> doService(Request<OrgDto> orgDtoRequest) throws ServiceException {
        OrgDto orgDto = orgDtoRequest.getData();
        Org parentOrg = orgRepository.findOne(orgDto.getPid());
        if (parentOrg == null) {
            throw new ServiceException(String.format("父机构[%s]不存在", orgDto.getPid()));
        }
        Org org = new Org();
        org.setName(orgDto.getName());
        org.setOd(orgDto.getOd());
        org.setParent(parentOrg);
        orgRepository.save(org);
        Response response = new Response(Constant.MSG_SUCCESS, true);
        return response;
    }
}
