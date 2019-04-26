package com.qif.cloud.mybatis.old.algorithm;

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * Created by Administrator on 2019/4/25 0025.
 */
public final class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {

    //availableTargetNames;配置的数据库名  shardingValue:表逻辑名,主键,传入值
    @Override
    public String doSharding(final Collection<String> availableTargetNames, final PreciseShardingValue<Integer> shardingValue) {

        int size = availableTargetNames.size();
        for (String each : availableTargetNames) {
            if (each.endsWith(shardingValue.getValue() % size + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
}