package com.flab.Mytube.domain;

import com.flab.Mytube.dto.TimeDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class User  extends TimeDTO {
  private Long id;
  private String email;
  private String password;
  private String nickname;
}
