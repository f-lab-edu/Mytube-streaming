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
import java.util.List;

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
    private String m3u8Url; // m3u8 파일의 URL

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
    private List<String> tsSegments;
    public List<String> getTsSegments(LocalTime time) {
        // 시간에 따라 ts 세그먼트를 반환하는 로직을 구현
        // 예를 들어, 현재 시간에 맞는 ts 세그먼트를 리스트로 반환
        return this.tsSegments;
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
