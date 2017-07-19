package com.andx.micro.management.service.channel;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceContext;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetComplexService;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.config.Constant;
import com.andx.micro.management.dto.ChannelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-13.
 */
@Component
@Service(path = "/channels", code = "queryChannels", method = MethodType.GET, name = "查询渠道", module = "management")
public class QueryChannels extends GetComplexService<Response<List<ChannelDto>>> {

    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response<List<ChannelDto>> doService(Map<String, String> prams, String path, ServiceContext context) throws ServiceException {
        return permissionClient.queryChannels(Constant.MODULE, prams.get(Constant.KEY_REQUEST_ID));
    }
}
