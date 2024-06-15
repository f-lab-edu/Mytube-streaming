package com.flab.Mytube.mapper;

import com.flab.Mytube.dto.streaming.LiveStatus;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;

import java.util.HashMap;
import java.util.Map;

public class HashMapping {

    @Resource(name = "redisTemplate")
    HashOperations<String, byte[], byte[]> hashOperations;

    HashMapper<Object, byte[], byte[]> mapper = new ObjectHashMapper();
    Map<String, LiveStatus> statusMapper = new HashMap<>();


    public void writeHash(String key, LiveStatus status) {
        Map<byte[], byte[]> mappedHash = mapper.toHash(status);
        hashOperations.putAll(key, mappedHash);
    }

    public LiveStatus loadHash(String key) {
        Map<byte[], byte[]> loadedHash = hashOperations.entries(key);
        return (LiveStatus) mapper.fromHash(loadedHash);
    }

    public void createLiveStatus(long id, String status, int currentTime) {
        LiveStatus liveStatus = new LiveStatus(id, status, currentTime);
    }

}