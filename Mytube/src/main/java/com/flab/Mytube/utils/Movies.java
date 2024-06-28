package com.flab.Mytube.utils;

import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import com.flab.Mytube.error.exceptions.DuplicatedPathException;
import lombok.NoArgsConstructor;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
@Component
public class Movies {
  public FFmpegBuilder segmentationTs(String masterPath, String path, File output, String tsName) {
    // ts 파일로 분할 및 분해 설정
    FFmpegBuilder builder = new FFmpegBuilder()
        .setInput(path) // 입력 소스
        .overrideOutputFiles(true)
        .addOutput(output.getAbsolutePath() + "/" + masterPath) // 저장경로
        .setFormat("hls")
        .addExtraArgs("-hls_time", "10") // 10초
        .addExtraArgs("-hls_list_size", "0")
        .addExtraArgs("-hls_segment_filename",
            output.getAbsolutePath() + "/" + tsName + "_%08d.ts") // 청크 파일 이름
        .done();
    return builder;
  }

  public File getFfmpegBuilder(String masterPath, int startIndex) {
    List<String> lines;
    StringBuilder sb = new StringBuilder();
    String base = masterPath.split("\\.")[0];
    sb.append(base).append(startIndex).append("_created.m3u8");
    String createdFilePath = sb.toString();

    Path directory = Paths.get(base);
    try {
      Files.createDirectories(directory);
    } catch (FileAlreadyExistsException e) {
    } catch (IOException e) {
      e.printStackTrace();
    }

    File file = new File(createdFilePath);
    try {
      if (!file.exists() && !file.createNewFile()) {
        return file;
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    try (
        Stream<String> stream = Files.lines(Paths.get(masterPath))) {
      lines = stream.collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

    StringBuilder playList = writePlayList(lines, startIndex);
    try (
        FileWriter writer = new FileWriter(file)) {
      writer.write(playList.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return file;
  }

  public StringBuilder writePlayList(List<String> lines, int startIndex) {
    StringBuilder playList = new StringBuilder();
    int index = 0;

    for (String line : lines) {
      if (line.startsWith("#EXTINF")) {
        if (index >= startIndex) {
          playList.append(line).append("\n");
        }
        index++;
        continue;
      }
      if (line.startsWith("#")) {
        playList.append(line).append("\n");
        continue;
      }
      if (index > startIndex) {
        playList.append(line).append("\n");
      }
    }
    return playList;
  }
}
