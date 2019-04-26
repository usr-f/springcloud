package com.qif.cloud.mybatis.old;

import com.qif.cloud.mybatis.old.config.ShardingConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by Administrator on 2019/4/24 0024.
 */
@SpringBootApplication
@MapperScan(value = "com.qif.cloud.mybatis.old.dao")
public class SpringMybatisOldApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringMybatisOldApplication.class, args);
    }
}
