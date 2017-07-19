package com.andx.micro.user.service.org;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.DeleteSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.model.Org;
import com.andx.micro.user.repository.OrgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-6-30.
 */
@Component
@Service(path = "/org/\\w+", code = "deleteOrg", method = MethodType.DELETE, name = "删除机构", module = "user")
public class DeleteOrg extends DeleteSampleService<Response> {

    @Autowired
    private OrgRepository orgRepository;

    @Override
    protected Response doService(Map<String, String> prams, String path) throws ServiceException {
        String idStr = path.substring(path.lastIndexOf("/") + 1);
        String [] ids = idStr.split(",");
        for (String id : ids) {
            Org org = orgRepository.findOne(Long.valueOf(id));
            if (org == null) {
                throw new ServiceException(String.format("机构[%s]不存在", id));
            }
            if (org.getSons().size() > 0) {
                throw new ServiceException(String.format("机构[%s]存在子机构", id));
            } else {
                orgRepository.delete(org);
            }
        }
        Response response = new Response(Constant.MSG_SUCCESS, true);
        return response;
    }
}
