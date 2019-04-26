package com.qif.cloud.web.admin.feign.controller;

import com.qif.cloud.web.admin.feign.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/4/22 0022.
 */
@RestController
public class AdminController {

    @Autowired
    private AdminService service;

    @RequestMapping(value = "hi/{message}" ,method = RequestMethod.GET)
    public String Hello(@PathVariable String message) {
        return service.Hello(message);
    }
}
