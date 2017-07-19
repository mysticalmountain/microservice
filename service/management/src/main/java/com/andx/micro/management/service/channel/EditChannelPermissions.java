package com.andx.micro.management.service.channel;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetComplexService;
import com.andx.micro.core.service.PutComplexService;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.PermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-13.
 */
@Component
@Service(path = "/channel/\\w+/permissions", code = "editChannelPermissions", method = MethodType.PUT, name = "编辑渠道权限", module = "management")
public class EditChannelPermissions extends PutComplexService<Request<List<Long>>, Response> {

    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response doService(Request<List<Long>> listRequest, String path, ServiceContext context) throws ServiceException {
        String tmp = path.substring(0, path.lastIndexOf("/"));
        String id = tmp.substring(tmp.lastIndexOf("/") + 1);
        listRequest.setChannelId(Constant.MODULE);
        return permissionClient.editChannelPermissions(Long.valueOf(id), listRequest);
    }
}
