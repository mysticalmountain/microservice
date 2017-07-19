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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-7-13.
 */
@Component
@Service(path = "/channels", code = "queryChannel", method = MethodType.GET, name = "查询渠道", module = "permission")
public class QueryChannels extends GetSampleService<Response<List<ChannelDto>>> {

    @Autowired
    private ChannelRepository channelRepository;

    @Override
    protected Response<List<ChannelDto>> doService(Map<String, String> prams, String path) throws ServiceException {
        List<Channel> channels = channelRepository.findAll();
        List<ChannelDto> channelDtos = new ArrayList<>();
        for (Channel channel : channels) {
            ChannelDto channelDto = new ChannelDto();
            BeanUtils.copyProperties(channel, channelDto);
            channelDtos.add(channelDto);
        }
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(channelDtos);
        return response;
    }
}
