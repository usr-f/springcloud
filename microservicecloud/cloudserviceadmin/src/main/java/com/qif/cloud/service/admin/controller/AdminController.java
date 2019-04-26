package com.qif.cloud.service.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/4/21 0021.
 */
@RestController
public class AdminController {

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/hi/{message}", method = RequestMethod.GET)
    public String Hello(@PathVariable String message){
        return String.format("Hi your message is : %s port is : %s", message, port);
    }
}
