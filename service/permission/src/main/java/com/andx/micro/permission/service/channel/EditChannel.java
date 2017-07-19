package com.andx.micro.permission.service.channel;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.PutSampleService;
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
@Service(path = "/channel/\\w+", code = "editChannel", method = MethodType.PUT, name = "编辑渠道", module = "permission")
public class EditChannel extends PutSampleService<Request<ChannelDto>, Response<ChannelDto>> {

    @Autowired
    private ChannelRepository channelRepository;

    @Override
    protected Response<ChannelDto> doService(Request<ChannelDto> channelDtoRequest, String path) throws ServiceException {
        String id = path.substring(path.lastIndexOf("/") + 1);
        Channel channel = channelRepository.findOne(Long.valueOf(id));
        if (channel == null) {
            throw new ServiceException(String.format("渠道[%s]不存在", id));
        }
        ChannelDto channelDto = channelDtoRequest.getData();
        if (channel.getCode().equals(channelDto.getCode())) {
            throw new ServiceException(String.format("渠道[%s]已存在", channelDto.getCode()));
        }
        channel.setCode(channelDto.getCode());
        channel.setName(channelDto.getName());
        channel = channelRepository.save(channel);
        BeanUtils.copyProperties(channel, channelDto);
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(channelDto);
        return response;
    }
}
