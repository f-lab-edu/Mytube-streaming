package com.flab.Mytube.dto.movie.request;

import com.flab.Mytube.utils.MoviePath;
import java.io.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
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
//    File file=new File(chunkFile);

    String chunckPath = chunkFile.getParent();
    log.info(" >>> >>> >>> request chunckpath >>> "+chunckPath);
    File file = new File(chunckPath);
    if(!file.exists()){
      file.mkdirs();
    }
    return chunckPath;
  }
}
