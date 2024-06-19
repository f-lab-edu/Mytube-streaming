package com.flab.Mytube.dto.streaming;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Getter
@RedisHash(value = "liveId", timeToLive = 30)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class LiveStatus implements Serializable {
    @Id
    private long liveId;
    private String status;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "kk:mm:ss")
    private LocalTime currentTime;


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastUpdated;

    public LiveStatus(long liveId) {
        this.liveId = liveId;
        this.currentTime = LocalTime.of(0, 0, 1);
        startLive();
    }

    public LiveStatus(String status, LocalTime time) {
        this.status = status;
        this.currentTime = time;
        this.lastUpdated = LocalDateTime.now();
    }

    public void startLive() {
        this.status = "LIVE_ON";
        this.lastUpdated = LocalDateTime.now();
    }

    public void stopLive() {
        this.status = "LIVE_STOP";
        this.lastUpdated = LocalDateTime.now();
    }

    public void endLive() {
        this.status = "LIVE_STOP";
        this.lastUpdated = LocalDateTime.now();
    }

    public void reservedLive() {
        this.status = "LIVE_RESERVED";
        this.lastUpdated = LocalDateTime.now();
    }

    public synchronized void updateCurrent(LocalTime time) {
        this.currentTime = time;
        this.lastUpdated = LocalDateTime.now();
    }

    public synchronized String getStatus() {
        return this.status;
    }

    public boolean isLiveOn() {
        if (this.status.equals("LIVE_ON"))
            return true;
        return false;
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("  >>>> >>> >>> live id ->").append(liveId).append("\n");
//        sb.append("  >>>> >>> >>> status ->").append(status).append("\n");
//        sb.append("  >>>> >>> >>> currentTime ->").append(currentTime).append("\n");
//        sb.append("  >>>> >>> >>> lastUpdated ->").append(lastUpdated.toString()).append("\n");
//        return sb.toString();
//    }

}
