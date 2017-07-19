package com.andx.micro.permission.service.service;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.dto.PageResponse;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.util.Constant;
import com.andx.micro.permission.dto.service.ServiceDto;
import com.andx.micro.permission.model.Service;
import com.andx.micro.permission.repository.ServiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by andongxu on 17-4-17.
 */
@Component
@com.andx.micro.api.core.Service(path = "/services", code = "queryServices", name = "查询服务", module = "permission")
public class QueryServices extends GetSampleService<PageResponse<List<ServiceDto>>> {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public PageResponse<List<ServiceDto>> doService(Map<String, String> stringMap, String path) throws ServiceException {
        Service qs = new Service();
        qs.setCode(stringMap.get("code"));
        qs.setMethod(stringMap.get("method") != null ? MethodType.valueOf(stringMap.get("method")) : null);
        qs.setIsAudit(stringMap.get("isAudit") != null ? Integer.valueOf(stringMap.get("isAudit")) : null);
        qs.setModule(stringMap.get("module"));
        Example<Service> example = Example.of(qs);
        int page = Integer.parseInt(stringMap.get("page") != null ? stringMap.get("page") : "0");
        int size = Integer.parseInt(stringMap.get("size") != null ? stringMap.get("size") : "20");
        Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "createdTime"));
        Page<Service> servicePage = serviceRepository.findAll(example, pageable);
        Iterator<Service> services = servicePage.iterator();
        List<ServiceDto> serviceDtos = new ArrayList<ServiceDto>();
        while (services.hasNext()) {
            Service service = services.next();
            ServiceDto serviceDto = new ServiceDto();
            BeanUtils.copyProperties(service, serviceDto);
            serviceDtos.add(serviceDto);
        }
        PageResponse response = new PageResponse(Constant.MSG_SUCCESS, true);
        response.setTotalPages(Long.valueOf(servicePage.getTotalPages()));
        response.setTotalRecords(Long.valueOf(servicePage.getTotalElements()));
        response.setCurrentPage(Long.valueOf(page));
        response.setData(serviceDtos);
        return response;
    }
}
