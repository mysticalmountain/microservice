package com.andx.micro.management.config;

import com.andx.micro.api.core.InitializationProjec;
import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ComplexService;
import com.andx.micro.api.core.module.service.SampleService;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;
import com.andx.micro.core.util.AopTargetUtils;
import com.andx.micro.management.client.PermissionClient;
import com.andx.micro.management.dto.RoleDto;
import com.andx.micro.management.dto.ServiceDto;
import com.andx.micro.management.dto.TmpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by andongxu on 17-5-3.
 */
@Component
public class InitializationService implements InitializationProjec<Void> {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());
    @Autowired(required = false)
    private List<SampleService<?, ?>> sampleServices;
    @Autowired(required = false)
    private List<ComplexService<?, ?>> complexServices;
    @Autowired
    private PermissionClient permissionClient;


    @Override
    public void init(Void aVoid) {
        log.info("初始化服务开始...");
        Service service = null;
        try {
            if (sampleServices != null) {
                for (SampleService sampleService : sampleServices) {
                    service = sampleService.getClass().getAnnotation(Service.class);
                    if (service == null) {
                        service = AopTargetUtils.getTarget(sampleService).getClass().getAnnotation(Service.class);
                    }
                    Request request = new Request();
                    request.setChannelId(Constant.MODULE);
                    ServiceDto serviceDto = new ServiceDto();
                    serviceDto.setPath(service.path());
                    serviceDto.setContent(service.name());
                    serviceDto.setCode(service.code());
                    serviceDto.setModule(service.module());
                    serviceDto.setSystem(service.system());
                    if (service.method() == null) {
                        serviceDto.setMethod(MethodType.GET);
                    } else {
                        serviceDto.setMethod(service.method());
                    }
                    request.setData(serviceDto);
                    Response response = permissionClient.initSevice(request);
                    System.out.println(response);
                }
            }

            if (complexServices != null) {
                for (ComplexService complexService : complexServices) {
                    service = complexService.getClass().getAnnotation(Service.class);
                    if (service == null) {
                        service = AopTargetUtils.getTarget(complexService).getClass().getAnnotation(Service.class);
                    }
                    Request request = new Request();
                    request.setChannelId(Constant.MODULE);
                    ServiceDto serviceDto = new ServiceDto();
                    serviceDto.setPath(service.path());
                    serviceDto.setContent(service.name());
                    serviceDto.setCode(service.code());
                    serviceDto.setModule(service.module());
                    serviceDto.setSystem(service.system());
                    request.setData(serviceDto);
                    Response response = permissionClient.initSevice(request);
                    System.out.println(response);
                }
            }
            log.info("初始化服务完成...");
        } catch (Exception e) {
            log.error("初始化服错误, service code [" + service.code() + "], ", e);
        }
    }
}
