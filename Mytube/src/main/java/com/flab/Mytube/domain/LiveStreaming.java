package com.flab.Mytube.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.flab.Mytube.dto.TimeDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LiveStreaming extends TimeDTO {

  private long id;
  private long chanelId; // 라이브 방송 호스트 id
  private long movieId; //방송하는 영상 Id
  private String title; //방송 제목

  private String contents; // 방송 내용
  private int userCount; // 시청자 수
  private int thumbsUp; // 좋아요 받은 수

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  private LocalDate reservedTime; //방송 예약 시간

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime updatedAt;
  // 방송 수정 시간(방제, 컨텐츠 등 ...), TODO: 방송 시간에 맞게 시작하도록
}
