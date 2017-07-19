package com.andx.micro.management;

import com.andx.micro.core.validator.ValidatorConfig;
import com.andx.micro.support.jpa.config.JpaConfig;
import com.andx.micro.support.session.SessionConfig;
import com.andx.micro.support.web.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * Created by andongxu on 16-10-31.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.andx.micro.management", "com.andx.micro.core.service"})
@Import({JpaConfig.class, ValidatorConfig.class, WebConfig.class, SessionConfig.class})
public class ManagementApp {

    public static void main(String[] args) {
        SpringApplication.run(ManagementApp.class, args);
    }
}
