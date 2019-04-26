package com.qif.cloud.zuul.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by Administrator on 2019/4/22 0022.
 */
@Component
public class WebAdminFeignFallBackProvider implements FallbackProvider{
    @Override
    public String getRoute() {
        return "web-admin-feign";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String s, Throwable throwable) {
        return new ClientHttpResponse() {
            /**
             * 网关向api服务请求失败了,但是消费者从客户端向网关api的请求是成功的
             * 不应该把api的404和500抛回给客户端
             * @return
             * @throws IOException
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                ObjectMapper objectMapper = new ObjectMapper();
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("status", 200);
                hashMap.put("message", "无法连接,请检查您的网络");
                return new ByteArrayInputStream(objectMapper.writeValueAsString(hashMap).getBytes("UTF-8"));
            }

            /**
             * 响应格式
             * @return
             */
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
    }
}
