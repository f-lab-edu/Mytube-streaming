package com.flab.Mytube.dto.movie.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class WatchLiveRequest {

  String chanelId;// or liveSource(.ts)
  int liveId;
}
