package com.flab.Mytube.dto.streaming;


import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@RedisHash(value = "liveId", timeToLive = 30)
public class LiveStatus {
    @Id
    private long liveId;
    private String status;
    private int currentTime;
    private LocalDateTime lastUpdated;

    public LiveStatus(String status, int time){
        this.status = status;
        this.currentTime=time;
        this.lastUpdated = LocalDateTime.now();
    }
}
