package com.qif.cloud.web.admin.ribbon.controller;

import com.qif.cloud.web.admin.ribbon.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2019/4/21 0021.
 */
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "hi/{message}", method = RequestMethod.GET)
    public String Hello(@PathVariable String message){

        return adminService.Hello(message);
    }
}
