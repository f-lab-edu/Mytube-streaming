package com.flab.Mytube.dto.streaming;


import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

//RedisHash: 설정한 값을 Redis의 key 값 prefix로 사용한다.
@Getter
@RedisHash(value = "live", timeToLive = 30)
public class LiveStatus {
    @Id
    private long liveId;
    private String status;
    private int currentTime;
    private LocalDateTime lastUpdated;

    @Builder
    public LiveStatus(long id, String status, int time){
        this.liveId=id;
        this.status = status;
        this.currentTime=time;
        this.lastUpdated = LocalDateTime.now();
    }

    public static LiveStatus create(long id, String status, int currentTime){
        LiveStatus liveStatus =new LiveStatus(id, status, currentTime);
        return liveStatus;
    }

    public void stop(){
        this.status = "MOVIE_STOP";
    }
    public void restart(){
        this.status="MOVIE_PLAY";
    }
    public void end(){
        this.status="MOVIE_END";
    }
}
