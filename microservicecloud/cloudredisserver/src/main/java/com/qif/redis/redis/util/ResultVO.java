package com.qif.redis.redis.util;

public class ResultVO {
    public static Result success(Object object){
        Result result = new Result();
        result.setStatus(true);
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }
    public static Result success(){
        return success(null);
    }
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setStatus(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
