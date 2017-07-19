package com.andx.micro.user.service.user;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ComplexService;
import com.andx.micro.api.core.module.service.SampleService;
import com.andx.micro.api.core.module.service.ServiceException;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;
import com.andx.micro.core.service.GetSampleService;
import com.andx.micro.core.service.PostSampleService;
import com.andx.micro.core.util.AopTargetUtils;
import com.andx.micro.core.util.Constant;
import com.andx.micro.user.client.PermissionClient;
import com.andx.micro.user.config.ServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by andongxu on 17-6-9.
 */
@Component
@Service(path = "/services/init", code = "initUserServices",method = MethodType.GET, name = "初始化服务", module = "user")
public class InitService extends GetSampleService<Response<Boolean>> {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());

    @Autowired
    private List<SampleService> sampleServices;
    @Autowired
    private List<ComplexService> complexServices;
    @Autowired
    private PermissionClient permissionClient;

    @Override
    protected Response<Boolean> doService(Map<String, String> prams, String path) throws ServiceException {
        Service service = null;
        try {
            if (sampleServices != null) {
                for (SampleService sampleService : sampleServices) {
                    service = sampleService.getClass().getAnnotation(Service.class);
                    if (service == null) {
                        service = AopTargetUtils.getTarget(sampleService).getClass().getAnnotation(Service.class);
                    }
                    Request request = new Request();
                    ServiceDto serviceDto = new ServiceDto();
                    serviceDto.setPath(service.path());
                    serviceDto.setContent(service.name());
                    serviceDto.setCode(service.code());
                    serviceDto.setModule(service.module());
                    serviceDto.setSystem(service.system());
                    serviceDto.setMethod(service.method());
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
            return Constant.RESPONSE_SUCCESS;
        } catch (Exception e) {
            log.error("初始化服错误, service code [" + service.code() + "], ", e);
            return Constant.RESPONSE_FAIL;
        }
    }
}
