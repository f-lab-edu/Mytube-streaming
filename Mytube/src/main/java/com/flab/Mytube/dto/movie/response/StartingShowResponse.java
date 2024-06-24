package com.flab.Mytube.dto.movie.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
// TODO: 사용 범위 잘 생각해보기
@ToString
@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class StartingShowResponse {

  private long id;
  private String url;
  private String title;
  private String contents;
  private int userCount;
  private int thumbsUp;
}


