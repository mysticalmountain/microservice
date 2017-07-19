package com.andx.micro.permission;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * Created by andongxu on 16-6-12.
 */
@Transactional
@Commit
@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Config.class})
public class BaseTest {

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
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }

}
