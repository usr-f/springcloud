package com.qif.cloud.web.admin.feign.service;

import com.qif.cloud.web.admin.feign.service.Hystrix.AdminServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2019/4/22 0022.
 */
@FeignClient(value = "service-admin",fallback = AdminServiceHystrix.class)
public interface AdminService {

    @RequestMapping(value = "hi/{message}" ,method = RequestMethod.GET)
    public String Hello(@PathVariable(value = "message") String message);
}
