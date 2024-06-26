package com.flab.Mytube.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Like {
  long id;
  long userId;
  long liveId;
}
