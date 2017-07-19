package com.andx.micro.permission.service.channel;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.DeleteSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.model.Channel;
import com.andx.micro.permission.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by andongxu on 17-7-13.
 */
@Component
@Service(path = "/channel/\\w+", code = "deleteChannel", method = MethodType.DELETE, name = "删除渠道", module = "permission")
public class DeleteChannel extends DeleteSampleService<Response> {

    @Autowired
    private ChannelRepository channelRepository;

    @Override
    protected Response doService(Map<String, String> prams, String path) throws ServiceException {
        String id = path.substring(path.lastIndexOf("/") + 1);
        Channel channel = channelRepository.findOne(Long.valueOf(id));
        if (channel == null) {
            throw new ServiceException(String.format("渠道[%s]不存在", id));
        }
        if (channel.getPermissions().size() > 0) {
            throw new ServiceException(String.format("渠道[%s]存在权限，不允许删除", id));
        }
        channelRepository.delete(channel);
        return new Response(Constant.MSG_SUCCESS, true);
    }
}
