package com.qif.redis.redis.util;

import lombok.Data;

@Data
public class Result<T> {
    /**
     * 状态
     */
    private Boolean status;
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 具体内容
     */
    private T data;

}
