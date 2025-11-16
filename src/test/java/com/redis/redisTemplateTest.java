package com.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-11-16
 * @Description: 测试类
 */
@SpringBootTest
public class redisTemplateTest {

    @Autowired
    public StringRedisTemplate stringRedisTemplate;

    @Test
    void stringTest(){
        stringRedisTemplate.opsForValue().set("key","value",100, TimeUnit.SECONDS);
        System.out.println(stringRedisTemplate.opsForValue().get("key"));
//        System.out.println(stringRedisTemplate.delete("key"));
        System.out.println(stringRedisTemplate.opsForValue().get("key"));
    }



}
