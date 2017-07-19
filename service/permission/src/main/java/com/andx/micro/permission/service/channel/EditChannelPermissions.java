package com.andx.micro.permission.service.channel;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PutSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.permission.PermissionDto;
import com.andx.micro.permission.dto.permission.ResourceDto;
import com.andx.micro.permission.model.Channel;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.repository.ChannelRepository;
import com.andx.micro.permission.repository.PermissionRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by andongxu on 17-7-13.
 */
@Component
@Service(path = "/channel/\\w+/permissions", code = "editChannelPermissions", method = MethodType.PUT, name = "编辑渠道权限", module = "permission")
public class EditChannelPermissions extends PutSampleService<Request<List<Long>>, Response> {

    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    protected Response doService(Request<List<Long>> listRequest, String path) throws ServiceException {
        String tmp = path.substring(0, path.lastIndexOf("/"));
        String id = tmp.substring(tmp.lastIndexOf("/") + 1);
        Channel channel = channelRepository.findOne(Long.valueOf(id));
        List<Long> permissionIds = listRequest.getData();
        Set<Permission> permissions = new HashSet<>();
        for (Long permissionId : permissionIds) {
            Permission permission = permissionRepository.findOne(permissionId);
            if (permission == null) {
                throw new ServiceException(String.format("权限[%s]不存在", permissionId));
            }
            permissions.add(permission);

        }
        channel.setPermissions(permissions);
        channelRepository.save(channel);
        Response response = new Response(Constant.MSG_SUCCESS, true);
        return response;
    }
}
