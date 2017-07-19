package com.andx.micro.permission;

import com.andx.micro.core.validator.ValidatorConfig;
import com.andx.micro.support.jpa.config.JpaConfig;
import com.andx.micro.support.session.SessionConfig;
import com.andx.micro.support.web.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by andongxu on 16-10-31.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.andx.micro.permission", "com.andx.micro.core.service"})
@Import({JpaConfig.class, ValidatorConfig.class, WebConfig.class, SessionConfig.class})
//@Profile("test")
public class PermissionApp {
    public static void main(String [] args) {
        SpringApplication.run(PermissionApp.class, args);
    }
}
