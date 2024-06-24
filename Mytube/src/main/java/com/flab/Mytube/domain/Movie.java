package com.flab.Mytube.domain;

import com.flab.Mytube.dto.TimeDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Movie extends TimeDTO {

  private long id;
  @NonNull
  private long chanelId; // 동영상 게시자
  @NonNull
  private String subject; // 영상 제목
  @NonNull
  private String url; // 영상 저장된 위치

}
