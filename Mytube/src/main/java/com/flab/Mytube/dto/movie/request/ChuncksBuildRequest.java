package com.flab.Mytube.dto.movie.request;

import com.flab.Mytube.utils.MoviePath;
import java.io.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

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
