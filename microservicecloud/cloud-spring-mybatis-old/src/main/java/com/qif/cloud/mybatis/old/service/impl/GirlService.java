package com.qif.cloud.mybatis.old.service.impl;

import com.qif.cloud.mybatis.old.dao.GirlMapper;
import com.qif.cloud.mybatis.old.model.Girl_info;
import com.qif.cloud.mybatis.old.service.IGirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/4/24 0024.
 */
@Service
public class GirlService implements IGirlService {

    @Autowired
    private GirlMapper girlMapper;

    @Override
    public void insertGirls(List<Girl_info> girls) {
        girlMapper.insertGirls(girls);
    }

    @Override
    public List<Girl_info> selectGirls(List<Integer> ids) {
        return girlMapper.selectGirls(ids);
    }
}
