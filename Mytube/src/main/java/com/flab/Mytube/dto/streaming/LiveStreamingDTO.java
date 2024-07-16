package com.flab.Mytube.dto.streaming;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.flab.Mytube.dto.TimeDTO;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LiveStreamingDTO extends TimeDTO {

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
  private LocalDateTime reservedTime;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime updatedAt; // TODO : 관련 코드 설계
}
