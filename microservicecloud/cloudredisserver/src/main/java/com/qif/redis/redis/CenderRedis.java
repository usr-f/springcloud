package com.qif.redis.redis;

import com.qif.redis.redis.util.Result;
import com.qif.redis.redis.util.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.*;


/**
 * Created by Administrator on 2019/4/14 0014.
 */
@RestController
@ResponseBody
public class CenderRedis {

    @Autowired
    private ShardedJedisPool convertJedisPool;

    /*
    测试连接
     */

    @GetMapping("/test")
    public String test(String key){
        ShardedJedis resource = convertJedisPool.getResource();
        //System.out.println(resource.ping());
        resource.zadd("k1",50d,"i");
        resource.zadd("k1",60d,"hate");
        resource.zadd("k1",70d,"spring");
        Set<String> k1 = resource.zrangeByScore("k1", 0, 100);
        for(Iterator iterator = k1.iterator();iterator.hasNext();){
            String str = (String)iterator.next();
            System.out.println(str);
        }
        resource.close();
        return key;
    }

    /*
    案例
    1.输入手机号,点击后随机生成位数字码,2分钟有效
    2.输入验证码,点击成功或失败
    3.每个手机号每天只能发送三次
     */
    @GetMapping("/doSend")
    public Result<String> doSend(String tel_no){
        ShardedJedis resource = null;
        //从前台获取电话号码的处理
        try{
            resource = convertJedisPool.getResource();
            //限制发3次的key
            String countKey = tel_no + ":count";
            //发验证码的key
            String codeKey = tel_no + ":code";
            String count = resource.get(countKey);
            if(count == null){
                resource.set(countKey,"1");
                //从现在到明天0点的时间毫秒差
                Calendar calendar = new GregorianCalendar();
                Date now = new Date();
                calendar.setTime(now);
                calendar.add(calendar.DATE,1);
                calendar.setTime(calendar.getTime());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                long tomTime = calendar.getTime().getTime();
                long curTime = System.currentTimeMillis();
                int timeStamp = (int)(tomTime - curTime)/1000;

                resource.setex(countKey,timeStamp,"1");
                //毫秒:resource.psetex(...)
                return ResultVO.success();
            }else{
                if(Integer.valueOf(count) < 3){
                    //验证码
                    //生成验证码
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0 ; i < 6 ; i++){
                        sb.append(new Random().nextInt(10));
                    }
                    //在缓存数据库中增加验证码
                    resource.setex(codeKey,60*2,sb.toString());
                    //累加验证码发送次数
                    resource.incr(countKey);
                    //返回成功消息
                    return ResultVO.success();
                }else{
                    return ResultVO.error(500,"发送验证码次数超出了限制");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(resource != null)
                resource.close();
        }
        return ResultVO.success();
    }
}
