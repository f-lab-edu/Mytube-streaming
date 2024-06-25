package com.flab.Mytube.dto.movie.request;

import lombok.Builder;
import lombok.Data;

// TODO: 삭제 해도 무방할 듯? Movie가 있잖아.
// TODO: 삭제하자 ㅋㅋ

@Data
@Builder
public class MovieDtailRequest {

  private int channel;
  private String subject;
  private String movieId;
}
