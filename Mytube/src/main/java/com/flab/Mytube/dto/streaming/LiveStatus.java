package com.flab.Mytube.dto.streaming;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Getter
@RedisHash(value = "liveId", timeToLive = 30)
@NoArgsConstructor
@AllArgsConstructor
public class LiveStatus implements Serializable {
    @Id
    private long liveId;
    private String status;
    private int currentTime;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
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
