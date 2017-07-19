package com.andx.micro.permission.service.channel;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.channel.ChannelDto;
import com.andx.micro.permission.model.Channel;
import com.andx.micro.permission.repository.ChannelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by andongxu on 17-7-13.
 */
@Component
@Service(path = "/channel/\\w+", code = "queryChannel", method = MethodType.GET, name = "查询渠道", module = "management")
public class QueryChannel extends GetSampleService<Response<ChannelDto>>{

    @Autowired
    private ChannelRepository channelRepository;

    @Override
    protected Response<ChannelDto> doService(Map<String, String> prams, String path) throws ServiceException {
        String id = path.substring(path.lastIndexOf("/") + 1);
        Channel channel = channelRepository.findOne(Long.valueOf(id));
        ChannelDto channelDto = new ChannelDto();
        BeanUtils.copyProperties(channel, channelDto);
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(channelDto);
        return response;
    }
}
