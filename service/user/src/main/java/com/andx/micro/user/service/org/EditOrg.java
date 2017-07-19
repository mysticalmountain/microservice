package com.andx.micro.user.service.org;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PutSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.dto.OrgDto;
import com.andx.micro.user.model.Org;
import com.andx.micro.user.repository.OrgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-7-3.
 */
@Component
@Service(path = "/org/\\w+", code = "editOrg", method = MethodType.PUT, name = "编辑机构", module = "user")
public class EditOrg extends PutSampleService<Request<OrgDto>, Response> {

    @Autowired
    private OrgRepository orgRepository;

    @Override
    protected Response doService(Request<OrgDto> orgDtoRequest, String path) throws ServiceException {
        String orgId = path.substring(path.lastIndexOf("/") + 1);
        Org org = orgRepository.findOne(Long.valueOf(orgId));
        OrgDto orgDto = orgDtoRequest.getData();
        org.setName(orgDto.getName());
        orgRepository.save(org);
        Response response = new Response(Constant.MSG_SUCCESS, true);
        return response;
    }
}
