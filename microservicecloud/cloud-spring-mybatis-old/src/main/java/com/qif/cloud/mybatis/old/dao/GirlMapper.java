package com.qif.cloud.mybatis.old.dao;

import com.qif.cloud.mybatis.old.model.Girl_info;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019/4/24 0024.
 */
@Mapper
public interface GirlMapper {

    List<Girl_info> selectGirls(List<Integer> id);

    void insertGirls(List<Girl_info> girls);

    void insertGirl(@Param(value = "girl") Girl_info girl);
}
