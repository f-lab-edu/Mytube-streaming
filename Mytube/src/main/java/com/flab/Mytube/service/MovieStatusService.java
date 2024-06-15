package com.flab.Mytube.service;

import com.flab.Mytube.dto.streaming.LiveStatus;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovieStatusService {
//    @Autowired
    private final RedisTemplate<String, Object> redisTemplate;

//    @Resource(name="redisTemplate")
//    HashOperations<String, String, Object> hashOperations;
    private HashOperations<String, String, Object> hashOperations;
    private final HashMapper<Object, String, Object> mapper = new ObjectHashMapper();



    private final String PREFIX = "LIVE_STATUS:";

    public void writeHash(String key, LiveStatus liveStatus) {
        Map<String, Object> mappedHash = mapper.toHash(liveStatus);
        hashOperations.putAll(key, mappedHash);
    }

    public LiveStatus loadHash(long liveId) {
        String key = PREFIX + liveId;
        Map<String, Object> status = hashOperations.entries(key);
        return (LiveStatus) mapper.fromHash(status);
    }

//    public LiveStatus getStatus(long liveId) {
//        String key = PREFIX + liveId;
//        Map<byte[], byte[]> status = hashOperations.entries(key);
//
//        return (LiveStatus) mapper.fromHash(status);
//    }
//
//    public void writeHash(String key, LiveStatus status) {
//
//        Map<byte[], byte[]> mappedHash = mapper.toHash(status);
//        hashOperations.putAll(key, mappedHash);
//    }
//    public void updateStatus(long id, String status, int currentTime) {
//        hashOperations = redisTemplate.opsForHash();
//        String key = PREFIX + id;
//        hashOperations.put(key, "status", status);
//        hashOperations.put(key, "current_time", currentTime);
//        hashOperations.put(key, "last_updated", System.currentTimeMillis());
//    }
}
