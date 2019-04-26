package com.qif.cloud.web.admin.ribbon.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2019/4/21 0021.
 */
@Configuration
public class RestTemplateConfiguration {

    @Bean
    //要访问的是负载均衡服务
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
