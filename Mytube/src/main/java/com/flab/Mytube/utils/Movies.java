package com.flab.Mytube.utils;

import com.flab.Mytube.dto.movie.request.ChuncksBuildRequest;
import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import com.flab.Mytube.dto.movie.request.MovieDtailRequest;
import com.flab.Mytube.error.exceptions.DuplicatedPathException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.springframework.beans.factory.annotation.Value;
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

@Slf4j
@RequiredArgsConstructor
@Component
public class Movies {

  // Movies 파일에서는 null 로 인식된g
  @Value("src/main/resources/static/origin")
  private static String savedPath;

  @Value("src/main/resources/static/hls")
  private static String hlsOutputPath;

  public static Path rootPath(FileUploadRequest request, String savedPath) {
    String fileName = request.getOriginFileName().split("\\.")[0];

    // savedPath: ./origin/channel-{id}/{subject} : 원본 저장 위치
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

  public static Path originPath(FileUploadRequest request) {
    Path result = rootPath(request, "src/main/resources/static/origin");
    return result;
  }

  public static Path hlsPath(FileUploadRequest request) {
    return rootPath(request, "src/main/resources/static/hls");
  }


  //  'src/main/resources/static/hls/channel-2/test20sec/test20sec.m3u8'
  public static File findHlsPathByChannelId(MovieDtailRequest request) {
    int channelId = request.getChannel();
    String movieId = request.getMovieId();
    String key = movieId.split("_")[0];
    StringBuilder sb = new StringBuilder();
    sb.append("src/main/resources/static/hls")
        .append("/channel-" + channelId).append("/")
        .append(key).append("/")
        .append(movieId); // TODO: api 주소가 괜찮을지 더 나은 방식은 없는지 고민해보기
    String filePath = sb.toString();
    return new File(filePath);
  }

  public static FFmpegBuilder segmentationTs(ChuncksBuildRequest request) {
    File output = request.getM3u8Path();
    FFmpegBuilder builder = new FFmpegBuilder()
        .setInput(request.getOriginPath()) // 입력 소스
        .overrideOutputFiles(true)
        .addOutput(request.getM3u8Path().toString() + "/" + request.getM3u8Name()) // 저장경로
        .setFormat("hls")
        .addExtraArgs("-hls_time", "10") // 10초
        .addExtraArgs("-hls_list_size", "0")
        .addExtraArgs("-hls_segment_filename",
            output.getAbsolutePath() + "/" + request.getName() + "_%08d.ts") // 청크 파일 이름
        .done();
    return builder;
  }

  public FFmpegBuilder segmentationTs(String m3u8FileName, String path, File output,
      String fileName) {
    // ts 파일로 분할 및 분해 설정
    FFmpegBuilder builder = new FFmpegBuilder()
        .setInput(path) // 입력 소스
        .overrideOutputFiles(true)
        .addOutput(output.getAbsolutePath() + "/" + m3u8FileName) // 저장경로
        .setFormat("hls")
        .addExtraArgs("-hls_time", "10") // 10초
        .addExtraArgs("-hls_list_size", "0")
        .addExtraArgs("-hls_segment_filename",
            output.getAbsolutePath() + "/" + fileName + "_%08d.ts") // 청크 파일 이름
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
