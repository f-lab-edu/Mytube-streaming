package com.flab.Mytube.domain;

import com.flab.Mytube.dto.TimeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends TimeDTO {

  private long id;
  private long channelId;
  private String subject;
  private String url;

}
