package com.andx.micro.user.service.org;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.dto.OrgDto;
import com.andx.micro.user.model.Org;
import com.andx.micro.user.repository.OrgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by andongxu on 17-7-3.
 */
@Component
@Service(path = "/org/\\w+", code = "queryOrg", name = "查询机构（按ID）", module = "user")
public class QueryOrg extends GetSampleService<Response<OrgDto>> {

    @Autowired
    private OrgRepository orgRepository;

    @Override
    protected Response<OrgDto> doService(Map<String, String> prams, String path) throws ServiceException {
        String orgId = path.substring(path.lastIndexOf("/") + 1);
        Org org = orgRepository.findOne(Long.valueOf(orgId));
        OrgDto orgDto = new OrgDto();
        orgDto.setId(org.getId());
        orgDto.setName(org.getName());
        orgDto.setOd(org.getOd());
        orgDto.setPid(org.getParent() != null ? org.getParent().getId() : null);
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(orgDto);
        return response;
    }
}
