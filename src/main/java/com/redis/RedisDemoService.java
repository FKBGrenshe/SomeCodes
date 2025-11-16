package com.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-11-16
 * @Description: reids示例服务
 */
@Service
public class RedisDemoService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public void setStringValueWithExpiration(String key, String value, long seconds){
        stringRedisTemplate.opsForValue().set(key,value,seconds, TimeUnit.SECONDS);
    }

    public String getStringValue(String key){
        String value = stringRedisTemplate.opsForValue().get(key);
        System.out.printf( key + ":" + value);
        return value;
    }

    public Boolean deleteKey(String key){
        Boolean result = stringRedisTemplate.delete(key);
        return result;
    }



}
