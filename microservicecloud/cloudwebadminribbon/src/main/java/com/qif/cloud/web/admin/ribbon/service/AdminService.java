package com.qif.cloud.web.admin.ribbon.service;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2019/4/21 0021.
 */
@Service
public class AdminService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "HelloError")
    public String Hello(String message){
                                                    //给eureka名字eureka就能帮你找到对应的服务端口
        return restTemplate.getForObject("Http://service-admin/hi/" + message , String.class);
    }

    public String HelloError(String message){
        return String.format("Hi Man , Your message is %s but request bad",message);
    }
}
