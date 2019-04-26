package com.qif.cloud.mybatis.old.config;

/**
 * Created by Administrator on 2019/4/25 0025.
 */

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.qif.cloud.mybatis.old.dao", sqlSessionTemplateRef = "shardSqlSessionTemplate")
public class MybatisConfig {

    @Bean(name = "shardSqlSessionFactory")
    public SqlSessionFactory shardSqlSessionFactory(@Qualifier("shardingDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        //bean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:application.yml"));
        return bean.getObject();
    }

    @Bean(name = "shardTransactionManager")
    public DataSourceTransactionManager shardTransactionManager(@Qualifier("shardingDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "shardSqlSessionTemplate")
    public SqlSessionTemplate shardSqlSessionTemplate(@Qualifier("shardSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
