package com.flab.Mytube.dto.movie.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieDtailRequest {

  private int chanelId;
  private String subject;
  private String movieId;
}
