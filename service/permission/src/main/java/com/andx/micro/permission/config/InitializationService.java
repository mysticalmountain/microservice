package com.andx.micro.permission.config;

import com.andx.micro.api.core.InitializationProjec;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.module.service.ComplexService;
import com.andx.micro.api.core.module.service.SampleService;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;
import com.andx.micro.core.util.AopTargetUtils;
import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.model.ResourceType;
import com.andx.micro.permission.repository.ResourceRepository;
import com.andx.micro.permission.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by andongxu on 17-5-2.
 */
@Component
public class InitializationService implements InitializationProjec<Void> {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());

    @Autowired
    private List<SampleService> sampleServices;

    @Autowired
    private List<ComplexService> complexServices;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ResourceRepository resourceRepository;


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
                    com.andx.micro.permission.model.Service s = serviceRepository.findByCode(service.code());
                    if (s == null) {
                        s = new com.andx.micro.permission.model.Service();
                        Resource resource = new Resource();
                        resource.setResourceType(ResourceType.SERVICE);
                        resourceRepository.save(resource);
                        s.setResource(resource);
                    }
                    s.setCode(service.code());
                    s.setPath(service.path());
                    s.setContent(service.name());
                    s.setSystem(service.system());
                    s.setModule(service.module());
                    s.setMethod(service.method());
                    serviceRepository.save(s);
                }
            }

            if (complexServices != null) {
                for (ComplexService complexService : complexServices) {
                    service = complexService.getClass().getAnnotation(Service.class);
                    if (service == null) {
                        service = AopTargetUtils.getTarget(complexService).getClass().getAnnotation(Service.class);
                    }
                    com.andx.micro.permission.model.Service s = serviceRepository.findByCode(service.code());
                    if (s == null) {
                        s = new com.andx.micro.permission.model.Service();
                        Resource resource = new Resource();
                        resource.setResourceType(ResourceType.SERVICE);
                        resourceRepository.save(resource);
                        s.setResource(resource);
                    }
                    s.setCode(service.code());
                    s.setPath(service.path());
                    s.setContent(service.name());
                    s.setSystem(service.system());
                    s.setModule(service.module());
                    s.setMethod(service.method());
                    serviceRepository.save(s);
                }
            }
            log.info("初始化服务完成...");
        } catch (Exception e) {
            log.error("初始化服错误, service code [" + service.code() + "], ");
        }
    }
}
