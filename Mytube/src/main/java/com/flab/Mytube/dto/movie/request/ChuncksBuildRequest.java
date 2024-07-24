package com.flab.Mytube.dto.movie.request;

import java.io.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChuncksBuildRequest {

  String name;
  String originPath;
  String m3u8Name;
  File m3u8Path;
}
