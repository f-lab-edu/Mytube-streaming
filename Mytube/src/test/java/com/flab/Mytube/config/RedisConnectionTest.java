package com.flab.Mytube.config;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

//import javax.annotation.PostConstruct;

@Component
public class RedisConnectionTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void testConnection() {
        try {
            String result = redisTemplate.getConnectionFactory().getConnection().ping();
            if ("PONG".equals(result)) {
                System.out.println("Redis connection successful!");
            } else {
                System.err.println("Redis connection failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Redis connection failed!");
        }
    }
}
