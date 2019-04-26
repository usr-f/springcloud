package com.qif.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2019/4/22 0022.
 */
@Component
public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //四个string值 pre(路由之前) routing(路由之时) post(路由之后) error(发送错误调用)
        return "pre";
    }

    //过滤顺序 0(最前)
    @Override
    public int filterOrder() {
        return 0;
    }

    //是否需要过滤
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //过滤器的具体业务代码
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        //logger.info("{} >>> {}", request.getMethod(), request.getRequestURL().toString());
        String token = request.getParameter("token");
        if (token == null){
            //logger.warn("Token is empty");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            try {
                HttpServletResponse response = context.getResponse();
                response.setContentType("text/html;charset=utf-8");
                context.getResponse().getWriter().write("非法请求");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            //logger.info("OK");
        }
        return null;
    }
}
