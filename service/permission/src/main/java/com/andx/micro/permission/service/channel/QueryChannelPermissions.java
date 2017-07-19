package com.andx.micro.permission.service.channel;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.service.PutSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.permission.PermissionDto;
import com.andx.micro.permission.dto.permission.ResourceDto;
import com.andx.micro.permission.model.Channel;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.repository.ChannelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-13.
 */
@Component
@Service(path = "/channel/\\w+/permissions", code = "queryChannelPermissions", method = MethodType.GET, name = "查询渠道权限", module = "permission")
public class QueryChannelPermissions extends GetSampleService<Response<List<PermissionDto>>> {

    @Autowired
    private ChannelRepository channelRepository;

    @Override
    protected Response<List<PermissionDto>> doService(Map<String, String> prams, String path) throws ServiceException {
        String tmp = path.substring(0, path.lastIndexOf("/"));
        String id = tmp.substring(tmp.lastIndexOf("/") + 1);
        Channel channel = channelRepository.findOne(Long.valueOf(id));
        Iterator<Permission> permissions = channel.getPermissions().iterator();
        List<PermissionDto> permissionDtos = new ArrayList<>();
        while (permissions.hasNext()) {
            PermissionDto permissionDto = new PermissionDto();
            Permission permission = permissions.next();
            BeanUtils.copyProperties(permission, permissionDto);
            Resource resource = permission.getResource();
            ResourceDto resourceDto = new ResourceDto();
            BeanUtils.copyProperties(resource, resourceDto);
            permissionDto.setResourceDto(resourceDto);
            permissionDtos.add(permissionDto);
        }
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(permissionDtos);
        return response;
    }
}
