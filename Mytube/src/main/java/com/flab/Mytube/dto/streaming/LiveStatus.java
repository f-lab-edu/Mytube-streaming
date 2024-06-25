package com.flab.Mytube.dto.streaming;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.flab.Mytube.constants.Status;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Getter
@Builder
@RedisHash(value = "liveId", timeToLive = 30)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class LiveStatus implements Serializable {

  @Id
  private long liveId;
  private Status status;
  private String m3u8Url; // m3u8 파일의 URL
  private long channelId;

  @JsonSerialize(using = LocalTimeSerializer.class)
  @JsonDeserialize(using = LocalTimeDeserializer.class)
  @JsonFormat(pattern = "HH:mm:ss")
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


  // TODO: 범위 밖의 데이터 index 를 반환하는 경우(error)
  public int getTsIndex() {
    // m3u8 파일을 파싱하여 ts 세그먼트 URL을 가져오는 로직을 구현
    int seconds =
        currentTime.getHour() * 60 * 60 + currentTime.getMinute() * 60 + currentTime.getSecond();
    int startIndex = seconds / 10;
    return startIndex;
  }

  private String getFileName() {
    String[] filepath = m3u8Url.split("/");
    return filepath[filepath.length - 1];
  }

  public String getBasePath(String tsName) {
    return m3u8Url.replace(getFileName(), tsName);
  }

  public void startLive() {
    this.status = Status.LIVE_ON;
    this.lastUpdated = LocalDateTime.now();
  }

  public void stopLive() {
    this.status = Status.LIVE_PAUSE;
    this.lastUpdated = LocalDateTime.now();
  }

  public void endLive() {
    this.status = Status.LIVE_END;
    this.lastUpdated = LocalDateTime.now();
  }

  public void updateCurrent(LocalTime time) {
    this.currentTime = time;
    this.lastUpdated = LocalDateTime.now();
  }

  public Status getStatus() {
    return this.status;
  }
  public boolean isEndLive(){
    if(status.equals(Status.LIVE_END))
      return true;
    return false;
  }
}
