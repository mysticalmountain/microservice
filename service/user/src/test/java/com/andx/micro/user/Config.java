package com.andx.micro.user;

import com.andx.micro.core.validator.ValidatorConfig;
import com.andx.micro.support.jpa.config.JpaConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * Created by andongxu on 17-1-7.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.andx.micro.permission", "com.andx.micro.core.service", "com.andx.micro.user"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {SpringBootApplication.class})})
@Import({JpaConfig.class, ValidatorConfig.class})
public class Config {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT, "application/json");
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set(HttpHeaders.ACCEPT_ENCODING, "utf-8");
        httpHeaders.set(HttpHeaders.CONTENT_ENCODING, "utf-8");
        httpHeaders.add(HttpHeaders.CONNECTION, "Keep-Alive");
        httpHeaders.add(HttpHeaders.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        return restTemplate;
    }
}
