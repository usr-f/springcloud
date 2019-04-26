package com.qif.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CloudRedisServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudRedisServerApplication.class, args);
	}

}
