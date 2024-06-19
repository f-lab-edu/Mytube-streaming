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

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
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

    public LiveStatus(long liveId, String url) {
        this.liveId = liveId;
        this.m3u8Url = url;
        this.currentTime = LocalTime.of(0, 0, 1);
        startLive();
    }


    public List<String> getTsSegmentUrls() {
        // m3u8 파일을 파싱하여 ts 세그먼트 URL을 가져오는 로직을 구현
        int seconds = currentTime.getHour() * 60 * 60 + currentTime.getMinute() * 60 + currentTime.getSecond();
        int startIndex = seconds / 10;
        List<String> tsSegmentUrls = parseM3u8(m3u8Url, startIndex);

        return tsSegmentUrls;
    }

    private List<String> parseM3u8(String m3u8Url, int start) {
        // m3u8 파일을 다운로드 및 파싱하여 ts 세그먼트 URL 리스트를 반환하는 로직 구현
        LinkedList<String> result = new LinkedList<>();
        String url = m3u8Url.replace(".m3m8", "_");
        int index = start;
        while (true) {
            StringBuilder sb = new StringBuilder();
            String tsIndex = String.format("%08d", index++);
            sb.append(url).append(tsIndex).append(".ts");
            File file = new File(sb.toString());
            if (file.exists() == false) {
                break;
            }
            result.add(sb.toString());
        }
        return result;
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
