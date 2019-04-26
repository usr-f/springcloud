package com.qif.cloud.mybatis.old.service;

import com.qif.cloud.mybatis.old.model.Girl_info;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/4/24 0024.
 */
public interface IGirlService {

    void insertGirls(List<Girl_info> girls);

    List<Girl_info> selectGirls(List<Integer> ids);

}
