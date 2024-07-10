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

  private static String savedPath = "src/main/resources/static/origin";
  private static String hlsOutputPath = "src/main/resources/static/hls";

//  public String m3u8Path() {
//    String outPath = originPath.replace(savedPath, hlsOutputPath).replace("/"+name+".mp4", "");
//    log.info(">>> >>> "+outPath);
//    this.m3u8Path=outPath;
//    return outPath;
//  }

//
//  String fileName;
//  File chunkFile;
//  String mp4Path;
//
//  @Autowired
//  private MoviePath moviePath;
//
//  public String chunckPath() {
////    File file=new File(chunkFile);
//
//    String chunckPath = chunkFile.getParent();
//    log.info(" >>> >>> >>> request chunckpath >>> "+chunckPath);
//    File file = new File(chunckPath);
//    if(!file.exists()){
//      file.mkdirs();
//    }
//    return chunckPath;
//  }
}
