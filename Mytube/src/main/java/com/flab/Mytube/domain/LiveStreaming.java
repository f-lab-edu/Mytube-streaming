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
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LiveStreaming extends TimeDTO {

  private long id;
  private long channelId;
  private long movieId;
  private String title;

  private String contents;
  private int userCount;
  private int thumbsUp;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  private LocalDate reservedAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime updatedAt;
  // TODO: 방송 시간에 맞게 시작하도록
}
