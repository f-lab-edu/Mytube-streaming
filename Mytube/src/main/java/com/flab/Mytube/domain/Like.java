package com.flab.Mytube.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Like {
  long id;
  long userId;
  long liveId;
}
