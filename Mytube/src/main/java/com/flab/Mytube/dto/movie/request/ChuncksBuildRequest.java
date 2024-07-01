package com.flab.Mytube.dto.movie.request;

import com.flab.Mytube.utils.MoviePath;
import java.io.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChuncksBuildRequest {

  String fileName;
  File chunkFile;
  String mp4Path;

  @Autowired
  private MoviePath moviePath;

  public String chunckPath() {
    String chunckPath = chunkFile.getPath();
    return chunckPath;
  }
}
