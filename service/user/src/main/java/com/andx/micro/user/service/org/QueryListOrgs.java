package com.andx.micro.user.service.org;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.dto.OrgDto;
import com.andx.micro.user.model.Org;
import com.andx.micro.user.repository.OrgRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-6-30.
 */
@Component
@Service(path = "/list-orgs/\\w+", code = "queryListOrgs", name = "查询某机构下的子机构列表", module = "user")
public class QueryListOrgs extends GetSampleService<Response<List<OrgDto>>> {

    @Autowired
    private OrgRepository orgRepository;

    @Override
    public Response<List<OrgDto>> doService(Map<String, String> prams, String path) throws ServiceException {
        String orgId = path.substring(path.lastIndexOf("/") + 1);
        Org org = orgRepository.findOne(Long.valueOf(orgId));
        if (org == null) {
            throw new ServiceException(String.format("机构[%s]不存在", orgId));
        }
        OrgDto orgDto = new OrgDto();
        orgDto.setName(org.getName());
        orgDto.setOd(org.getOd());
        orgDto.setId(org.getId());
        List<OrgDto> orgDtos = new ArrayList<>();
        orgDtos.add(orgDto);
        fetch(orgDtos, org);
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(orgDtos);
        return response;
    }

    private void fetch(List<OrgDto> orgDtos, Org org) {
        List<Org> sons = org.getSons();
        for (Org sonOrg : sons) {
            OrgDto orgDto = new OrgDto();
            orgDto.setName(sonOrg.getName());
            orgDto.setOd(sonOrg.getOd());
            orgDto.setId(sonOrg.getId());
            orgDto.setPid(sonOrg.getParent().getId());
            orgDtos.add(orgDto);
            fetch(orgDtos, sonOrg);
        }
    }

}
