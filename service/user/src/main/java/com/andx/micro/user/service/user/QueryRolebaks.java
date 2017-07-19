package com.andx.micro.user.service.user;

import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.dto.RolebakDto;
import com.andx.micro.user.model.Rolebak;
import com.andx.micro.user.repository.RolebakRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-5-5.
 */
@Component
@Service(path = "/rolebaks", code = "rolebaks", name = "查询用户角色bak", module = "user")
public class QueryRolebaks extends GetSampleService<Response<List<RolebakDto>>> {

    @Autowired
    private RolebakRepository rolebakRepository;

    @Override
    protected Response<List<RolebakDto>> doService(Map<String, String> prams, String path) throws ServiceException {
        List<Rolebak> rolebaks = rolebakRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
        List<RolebakDto> rolebakDtos = new ArrayList<>();
        for (Rolebak rolebak : rolebaks) {
            RolebakDto rolebakDto = new RolebakDto();
            BeanUtils.copyProperties(rolebak, rolebakDto);
            rolebakDtos.add(rolebakDto);
        }
        Response response = new Response(Constant.MSG_SUCCESS, true);
        response.setData(rolebakDtos);
        return response;
    }
}
