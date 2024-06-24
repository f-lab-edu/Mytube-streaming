package com.flab.Mytube.domain;

import com.flab.Mytube.dto.TimeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends TimeDTO {

  private long id;
  private long chanelId; // 동영상 게시자
  private String subject; // 영상 제목
  private String url; // 영상 저장된 위치

}
