package com.andx.micro.user.config;

import com.andx.micro.api.core.InitializationProjec;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.module.service.ComplexService;
import com.andx.micro.api.core.module.service.SampleService;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;
import com.andx.micro.core.util.AopTargetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by andongxu on 17-5-3.
 */
@Component
public class InitializationService implements InitializationProjec<Void> {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private List<SampleService> sampleServices;
    @Autowired
    private List<ComplexService> complexServices;
    //    @Autowired
//    private InitServiceClient initServiceClient;
//    @Autowired
//    private LoadBalancerClient loadBalancer;

    private String uri = "http://www.micro.com/permission/service/services/init";


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
                    ServiceDto serviceDto = new ServiceDto();
                    serviceDto.setPath(service.path());
                    serviceDto.setContent(service.name());
                    serviceDto.setCode(service.code());
                    serviceDto.setModule(service.module());
                    serviceDto.setSystem(service.system());
                    serviceDto.setMethod(service.method());
                    request.setData(serviceDto);
//                    ServiceInstance serviceInstance = loadBalancer.choose("permission-service");
//                    URI u = serviceInstance.getUri();
                    Response response = restTemplate.postForObject(uri, request, Response.class);
                    System.out.println(response);
                    try {

//                        initServiceClient.serviceInit(request);
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
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
//                    Response response = initServiceClient.serviceInit(request);
                    Response response = restTemplate.postForObject(uri, request, Response.class);
                }
            }
            log.info("初始化服务完成...");
        } catch (Exception e) {
            log.error("初始化服错误, service code [" + service.code() + "], ", e);
        }
    }
}
