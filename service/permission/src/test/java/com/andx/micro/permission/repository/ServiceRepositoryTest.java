package com.andx.micro.permission.repository;

import com.andx.micro.api.core.MethodType;
import com.andx.micro.permission.BaseTest;
import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.model.ResourceType;
import com.andx.micro.permission.model.Service;
import com.netflix.discovery.converters.Auto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;

/**
 * Created by andongxu on 17-6-13.
 */
public class ServiceRepositoryTest extends BaseTest {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    @Test
    public void queryService() {
        List<Service> services = serviceRepository.findService(3, 3);
        for (Service service : services) {
            System.out.println(service);
        }
    }

    @Test
    public void save() {
        Resource resource = new Resource();
        resource.setResourceType(ResourceType.SERVICE);
        resourceRepository.save(resource);
        Service service = new Service();
        service.setMethod(MethodType.GET);
        service.setCode("103");
        service.setPath("/103");
        service.setResource(resource);
        service.setContent("103");
        serviceRepository.save(service);
    }

    @Test
    public void query2() {
        com.andx.micro.permission.model.Service qs = new com.andx.micro.permission.model.Service();
        qs.setCode("n");
        qs.setContent("角色");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("code", matcher -> matcher.contains()).withMatcher("content", matcher -> matcher.contains());
        Example<Service> example = Example.of(qs, exampleMatcher);
        List<com.andx.micro.permission.model.Service> services = serviceRepository.findAll(example);
        for (Service service : services) {
            System.out.println(service.getCode() + "\t" + service.getContent());
        }
    }
}
