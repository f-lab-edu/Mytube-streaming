package com.flab.Mytube.config;

import com.flab.Mytube.dto.streaming.LiveStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Collections;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.port}")
    int port;
    @Value("${spring.redis.host}")
    String host;
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .useSsl().and()
                .commandTimeout(Duration.ofSeconds(2))
                .shutdownTimeout(Duration.ZERO)
                .build();

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port), clientConfig);
    }
    @Bean
    RedisTemplate<String, Object> statusTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer(LiveStatus.class));// <<< 각각의 DTO 만들어서 반환
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer(LiveStatus.class));// <<<<
        return template;
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration defaults = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(6));
        RedisCacheManager cacheManager = RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig())
                .transactionAware()
                .withInitialCacheConfigurations(Collections.singletonMap("predefined",
                        RedisCacheConfiguration.defaultCacheConfig().disableCachingNullValues()))
                .cacheDefaults(defaults)
                .build();
        return cacheManager;
    }

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate (RedisConnectionFactory connectionFactory){
//        RedisTemplate <String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
//
////        redisTemplate.setKeySerializer(new StringRedisSerializer());
////        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer()); // JSON 포맷으로 저장
////        redisTemplate.setConnectionFactory(connectionFactory);
////                redisTemplate.setValueSerializer(new StringRedisSerializer());
//
//        return redisTemplate;
//    }

//    @Bean
//    StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        StringRedisTemplate template = new StringRedisTemplate();
//        template.setConnectionFactory(redisConnectionFactory);
//        return template;
//    }
}
