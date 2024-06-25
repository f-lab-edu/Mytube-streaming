package com.flab.Mytube.dto.movie.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieDtailRequest {

  private int channel;
  private String subject;
  private String movieId;
}
