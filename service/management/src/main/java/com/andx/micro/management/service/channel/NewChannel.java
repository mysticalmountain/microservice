package com.andx.micro.management.service.channel;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PostComplexService;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.ChannelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-7-13.
 */
@Component
@Service(path = "/channel", code = "newChannel", method = MethodType.POST, name = "新建渠道", module = "management")
public class NewChannel extends PostComplexService<Request<ChannelDto>, Response>{

    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response doService(Request<ChannelDto> channelDtoRequest, ServiceContext context) throws ServiceException {
        channelDtoRequest.setChannelId(Constant.MODULE);
        return permissionClient.newChannel(channelDtoRequest);
    }
}
