package com.andx.micro.permission.service.channel;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PostSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.channel.ChannelDto;
import com.andx.micro.permission.model.Channel;
import com.andx.micro.permission.repository.ChannelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 17-7-13.
 */
@Component
@Service(path = "/channel", code = "newChannel", method = MethodType.POST, name = "新建渠道", module = "permission")
public class NewChannel extends PostSampleService<Request<ChannelDto>, Response<ChannelDto>> {

    @Autowired
    private ChannelRepository channelRepository;

    @Override
    protected Response<ChannelDto> doService(Request<ChannelDto> channelDtoRequest) throws ServiceException {
        ChannelDto channelDto = channelDtoRequest.getData();
        Channel channel = channelRepository.findByCode(channelDto.getCode());
        if (channel != null) {
            throw new ServiceException(String.format("渠道[%s]已存在", channelDto.getCode()));
        }
        channel = new Channel();
        BeanUtils.copyProperties(channelDto, channel);
        channel = channelRepository.save(channel);
        channelDto.setId(channel.getId());
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(channelDto);
        return response;
    }
}
