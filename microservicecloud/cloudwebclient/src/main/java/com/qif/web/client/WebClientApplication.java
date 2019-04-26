package com.qif.web.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by Administrator on 2019/4/21 0021.
 */
@SpringBootApplication
@EnableEurekaClient
public class WebClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebClientApplication.class, args);
    }
}
