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

    public LiveStatus(long liveId){
        this.liveId=liveId;
        this.status="LIVE_ON";
        this.lastUpdated =LocalDateTime.now();
        this.currentTime= 0;
    }

    public LiveStatus(String status, int time){
        this.status = status;
        this.currentTime=time;
        this.lastUpdated = LocalDateTime.now();
    }

    public void changeState(String status){
        switch (status){
            case "restart":
                this.status="LIVE_ON";
                break;
            case "stop":
                this.status="LIVE_STOP";
                break;
            case "end":
                this.status="LIVE_END";
                break;
            default:
                break;
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("  >>>> >>> >>> live id ->").append(liveId).append("\n");
        sb.append("  >>>> >>> >>> status ->").append(status).append("\n");
        sb.append("  >>>> >>> >>> currentTime ->").append(currentTime).append("\n");
        sb.append("  >>>> >>> >>> lastUpdated ->").append(lastUpdated.toString()).append("\n");
        return sb.toString();
    }

}
