package com.qif.cloud.mybatis.old.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.qif.cloud.mybatis.old.algorithm.DatabaseShardingAlgorithm;
import com.qif.cloud.mybatis.old.algorithm.TablePreciseShardingAlgorithm;
import com.qif.cloud.mybatis.old.algorithm.TableRangeShardingAlgorithm;
import io.shardingsphere.core.api.ShardingDataSourceFactory;
import io.shardingsphere.core.api.config.ShardingRuleConfiguration;
import io.shardingsphere.core.api.config.TableRuleConfiguration;
import io.shardingsphere.core.api.config.strategy.StandardShardingStrategyConfiguration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2019/4/25 0025.
 */
@Configuration
public class ShardingConfig {

    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.ds0.druid")
    @Bean(name = "ds_0")
    public DataSource dataSource0(){
        return new DruidDataSource();
    }

    @ConfigurationProperties(prefix = "spring.datasource.ds1.druid")
    @Bean(name = "ds_1")
    public DataSource dataSource1(){
        return new DruidDataSource();
    }

    @Bean(name = "shardingDataSource")
    public DataSource getDataSource(@Qualifier("ds_0") DataSource ds_0, @Qualifier("ds_1") DataSource ds_1) throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getGirlTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getGirlSpecTableRuleConfiguration());
        //两表建立关系
        shardingRuleConfig.getBindingTableGroups().add("t_girl, t_girl_spec");
        //StandardShardingStrategy  标准分片策略,提供对sql中=,in,and between的分片操作支持(只支持单分片键)
        //ComplexShardingStrategy 复合分片策略,提供对sql中=,in,and between的分片操作支持(支持多分片键)
        //InlineShardingStrategy 使用groovy的inline语言,对sql中=和in提供分片操作支持(支持单分片键) 例:demo_ds_${user_id%2}
        //HintShardingStrategy 通过Hint而非SQL解析的方式分片的策略
        //NoneShardingStrategy 无分片策略
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new DatabaseShardingAlgorithm()));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("spec_id", new TablePreciseShardingAlgorithm(), new TableRangeShardingAlgorithm()));
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds_0", ds_0);
        dataSourceMap.put("ds_1", ds_1);
        Properties properties = new Properties();
//        properties.setProperty("sql.show", Boolean.TRUE.toString());
        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new HashMap<String, Object>(), properties);
    }

    private static TableRuleConfiguration getGirlTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("t_girl");
        result.setActualDataNodes("ds_${0..1}.t_girl_${[0, 1]}");
        result.setKeyGeneratorColumnName("id");
        return result;
    }

    private static TableRuleConfiguration getGirlSpecTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("t_girl_spec");
        result.setActualDataNodes("ds_${0..1}.t_girl_spec_${[0, 1]}");
        result.setKeyGeneratorColumnName("spec_id");
        return result;
    }
}
