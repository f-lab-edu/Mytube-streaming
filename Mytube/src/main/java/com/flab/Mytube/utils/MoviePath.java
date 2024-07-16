package com.flab.Mytube.utils;

import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import com.flab.Mytube.error.exceptions.DuplicatedPathException;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Component;

@Component
public class MoviePath {

  private static String savedPath = "src/main/resources/static/origin";

  private static String hlsOutputPath = "src/main/resources/static/hls";


  public Path originRootPath(FileUploadRequest request) {
    return rootPath(request, savedPath);
  }

  public Path outputRootPath(FileUploadRequest request) {
    return rootPath(request, hlsOutputPath);
  }

  private Path rootPath(FileUploadRequest request, String savedPath) {
    String fileName = request.getOriginFileName().split("\\.")[0];
    String path = savedPath + "/channel-" + request.getChannelId() + "/" + fileName;
    Path filepath = validPath(path);
    return filepath;
  }

  public File chunckPath(String originPath) {
    String outPath = originPath.replace(savedPath, hlsOutputPath).replace(".mp4", ".m3u8");
    String m3u8Dir = new File(outPath).getParent();
    return makeDir(m3u8Dir);
  }

  public static String chunkPathStr(String originPath) {
    String outPath = originPath.replace(savedPath, hlsOutputPath).replace(".mp4", ".m3u8");
    return outPath;
  }


  public static Path validPath(String path) {
    Path filepath = null;
    try {
      filepath = Paths.get(path);
      Files.createDirectories(filepath);
    } catch (FileAlreadyExistsException e) {
      throw new DuplicatedPathException("이미 업로드한 동영상 입니다.");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return filepath;
  }

  public static File makeDir(String path) {
    File output = new File(path);
    if (!output.exists()) {
      output.mkdirs();
    }
    return output;
  }
}
