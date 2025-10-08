package com.cts.hr.config;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class RedisCleanup {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("{spring.app.caches}")
    private String prefix;

    @PreDestroy
    public void clearRedisCache(){
        List<String> redisPrefix = Arrays.asList(prefix.split(","));
        if (redisTemplate == null || redisPrefix.isEmpty()) return;
        redisPrefix.forEach( prefix ->
        {
            Set<String> keys = redisTemplate.keys(prefix +"*");
            if(keys != null && !keys.isEmpty()) redisTemplate.delete(keys);
        });

        System.out.println("Successfully cleared redis cache on shutdown");
    }
}
