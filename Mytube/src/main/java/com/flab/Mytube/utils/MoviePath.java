package com.flab.Mytube.utils;

import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import com.flab.Mytube.error.exceptions.DuplicatedPathException;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;

public class MoviePath {
  @Value("src/main/resources/static/origin")
  private static String savedPath;

  @Value("src/main/resources/static/hls")
  private static String hlsOutputPath;

  public static Path originRootPath(FileUploadRequest request){
    return rootPath(request, savedPath);
  }

  public static Path outputRootPath(FileUploadRequest request){
    return rootPath(request, hlsOutputPath);
  }

  public static String outputRootPath(int channelId, String movieId, String key){
    StringBuilder sb = new StringBuilder();
    sb.append(hlsOutputPath).append("/channel-" + channelId).append("/").append(key).append("/")
        .append(movieId);
    return sb.toString();
  }

  public static Path rootPath(FileUploadRequest request, String savedPath) {
    String fileName = request.getOriginFileName().split("\\.")[0];
    String path = savedPath + "/channel-" + request.getChannelId() + "/" + fileName;
    Path filepath = null;
    try {
      filepath = Paths.get(path);
      Files.createDirectories(filepath); // 디렉토리 생성
    } catch (FileAlreadyExistsException e) {
      throw new DuplicatedPathException("이미 업로드한 동영상 입니다.");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return filepath;
  }

  public static File resultFile(String path) {
    File output = new File(path);
    if (!output.exists()) {
      output.mkdirs();
    }
    return output;
  }
}
