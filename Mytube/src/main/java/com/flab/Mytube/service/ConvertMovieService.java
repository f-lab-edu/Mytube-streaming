package com.flab.Mytube.service;

import com.flab.Mytube.domain.Movie;
import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import com.flab.Mytube.dto.movie.request.MovieDtailRequest;
import com.flab.Mytube.mappers.MovieMapper;
import com.flab.Mytube.utils.Movies;
import com.flab.Mytube.utils.Validations;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.progress.Progress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConvertMovieService {

  private final MovieMapper movieMapper;
  private final FFmpeg fFmpeg;
  private final FFprobe fFprobe;
  private final Movies movie = new Movies();
  private static Validations validation;

  @Value("src/main/resources/static/origin")
  private String savedPath;

  @Value("src/main/resources/static/hls")
  private String hlsOutputPath;

  @Value("src/main/resources/static/mp4")
  private String mp4OutputPath;

  //동영상 업로드
  @Transactional
  public void uploadMovie(FileUploadRequest request) {
    if (request.isEmptyFile()) {
      System.err.println("파일이 입력되지 않았습니다.");
      return;
    }
    // 파일 경로 지정
    String fileName = request.getFile().getOriginalFilename();
    Path filepath = movie.createPath(request, savedPath);
    filepath = filepath.resolve(fileName);

    // 파일 작성하기(복사)
    try (OutputStream os = Files.newOutputStream(filepath)) {
      byte[] bytes = request.getFile().getBytes();
      Files.write(filepath, bytes);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
//  filepath 경로에 파일 저장
    movieBuilder(filepath, request);
  }


  private void movieBuilder(Path filepath, FileUploadRequest request) {
    String path = filepath.toString();
    String outPath = movie.createPath(request, hlsOutputPath).toString(); // 저장 위치 생성
    File output = movie.resultFile(outPath);

    String fileName = request.getOriginFileName().split("\\.")[0];
    String tsName = fileName;

//  ts 파일로 분할 및 분해 설정
    String source = fileName + ".m3m8";
    FFmpegBuilder builder = movie.segmentationTs(source, path, output, tsName);

    // builder 실행
    run(builder);
    request.addPath(output.getPath(), source);
    movieMapper.save(request);
  }


  private void run(FFmpegBuilder builder) {
    FFmpegExecutor executor = new FFmpegExecutor(fFmpeg, fFprobe);

    executor
        .createJob(builder, progress -> {
          log.info("progress ==> {}", progress);
          if (progress.status.equals(Progress.Status.END)) {
            log.info("============================= JOB FINISHED =============================");
          }
        })
        .run();
  }

  public File getLiveFile(MovieDtailRequest request) {
    int chanelId = request.getChanelId();
    String movieId = request.getMovieId();
    // movie 의 id 가 입력된 경우

    if (validation.isNumeric(movieId)) {
      return getLiveFile(Long.valueOf(movieId));
    }
    // movie 의 .ts 파일 이름이 입력된 경우
    String key = movieId.split("_")[0];
    StringBuilder sb = new StringBuilder();
    sb.append(hlsOutputPath).append("/chanel-" + chanelId).append("/").append(key).append("/")
        .append(movieId);
    String filePath = sb.toString();
    return new File(filePath);
  }

  // id 를 통해 .m3m8 파일이 저장된 url 가져올 수 있도록
  public File getLiveFile(Long fileId) {
    Movie movie = movieMapper.findByMovieId(fileId);
    String filePath = movie.getUrl();

    return new File(filePath);
  }

  public void delete(long movieId){
    movieMapper.delete(movieId);
  }

  public List<Movie> getLiveLists(long chanelId) {
    return movieMapper.findByChanelId(chanelId);
  }
}
