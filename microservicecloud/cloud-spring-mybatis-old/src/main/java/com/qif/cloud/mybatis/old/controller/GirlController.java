package com.qif.cloud.mybatis.old.controller;

import com.qif.cloud.mybatis.old.dao.GirlMapper;
import com.qif.cloud.mybatis.old.model.Girl_info;
import com.qif.cloud.mybatis.old.service.IGirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * sharding-jdbc demo
 * Created by Administrator on 2019/4/24 0024.
 */
@RestController
public class GirlController {

    @Autowired
    private IGirlService girlService;

    @Autowired
    private GirlMapper girlMapper;

    @RequestMapping(value = "/girl/batchquery", method = RequestMethod.GET)
    public List<Girl_info> selectGirls(List<Integer> ids){
        return girlService.selectGirls(ids);
    }

    //test:[{"name":"zhangsan","age":"15"},{"name":"wangwu","age":"12"},{"name":"lisi","age":"1"},{"name":"zhangsan","age":"15"}](postman格式)
    //sharding似乎不支持批量插入
    @RequestMapping(value = "/girl/batchinsert", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public void insertGirls(@RequestBody List<Girl_info> girls){
        girlService.insertGirls(girls);
    }

    @RequestMapping(value = "/girl/insert", method = RequestMethod.POST)
    public void insertGirl(@RequestBody Girl_info girl){
        girlMapper.insertGirl(girl);
    }
}
