package com.qif.redis.redis.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/15 0015.
 */
@Configuration
public class JedisConfiguration {

    @Autowired
    private RedisConfig redisConfig;

    @Bean
    public ShardedJedisPool convertJedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisConfig.getMaxTotal());
        jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
        jedisPoolConfig.setTestOnBorrow(redisConfig.getTestOnBorrow());
        List<JedisShardInfo> jedisShardInfoList = new ArrayList<>();
        jedisShardInfoList.add(new JedisShardInfo(redisConfig.getHost(),redisConfig.getPort()));
        return new ShardedJedisPool(jedisPoolConfig, jedisShardInfoList);
    }
}
