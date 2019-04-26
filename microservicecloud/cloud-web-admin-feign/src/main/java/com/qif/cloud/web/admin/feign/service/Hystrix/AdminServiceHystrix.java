package com.qif.cloud.web.admin.feign.service.Hystrix;

import com.qif.cloud.web.admin.feign.service.AdminService;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/4/22 0022.
 */
@Component
public class AdminServiceHystrix implements AdminService{
    @Override
    public String Hello(String message) {
        return String.format("Hi Man , Your message is %s but request bad",message);
    }
}
