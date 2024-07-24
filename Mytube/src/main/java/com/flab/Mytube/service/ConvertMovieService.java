package com.flab.Mytube.service;

import com.flab.Mytube.domain.Movie;
import com.flab.Mytube.dto.movie.request.ChuncksBuildRequest;
import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import com.flab.Mytube.dto.movie.request.MovieDtailRequest;
import com.flab.Mytube.error.exceptions.NoDataSubmitException;
import com.flab.Mytube.kafka.Producer;
import com.flab.Mytube.mappers.MovieMapper;
import com.flab.Mytube.utils.MoviePath;
import com.flab.Mytube.utils.Movies;
import com.flab.Mytube.utils.Validations;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
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
  private final MoviePath moviePath;

  @Autowired
  Producer producer;

  @Transactional
  public void uploadMovie(FileUploadRequest request) {
    if (request.isEmptyFile()) {
      throw new NoDataSubmitException("파일을 제출하지 않았습니다.");
    }

    String fileName = request.getFile().getOriginalFilename();
    Path originPath = moviePath.originRootPath(request);
    originPath = originPath.resolve(fileName);

    try (OutputStream os = Files.newOutputStream(originPath)) {
      byte[] bytes = request.getFile().getBytes();
      Files.write(originPath, bytes);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    String data = originPath.toString();

    String key = fileName.split("\\.")[0];
//    producer.send(data);
    producer.send("videoPath", key, data);
//    request.addPath(MoviePath.chunkPathStr(originPath.toString()));
//    movieMapper.save(request);
  }


//  @KafkaListener(topics = "videoPath", groupId = "myGroup", containerFactory = "kafkaListenerContainerFactory")
  public void segment(ConsumerRecord<String, String> data) {
    String originPath = data.value();
    File chunckPath = moviePath.chunckPath(originPath);
    String fileName = chunckPath.getName().split("\\.")[0];

    ChuncksBuildRequest chunkBuilder = ChuncksBuildRequest.builder()
        .name(fileName)
        .originPath(originPath)
        .m3u8Name(fileName + ".m3u8")
        .m3u8Path(chunckPath)
        .build();
    FFmpegBuilder builder = Movies.segmentationTs(chunkBuilder);

    try {
      run(builder);
    } catch (IllegalArgumentException e) {
      log.info("N/A error ocuuer");
    } catch (Exception e) {
      log.info("영상 변환 중 에러가 발생했습니다. 다시 시도해주세요.");
    }
  }

  private void run(FFmpegBuilder builder) throws Exception {
    FFmpegExecutor executor = new FFmpegExecutor(fFmpeg, fFprobe);

    executor.createJob(builder).run();
  }


  public File movieFilePath(MovieDtailRequest request) {
    String movieId = request.getMovieId();

    if (Validations.isNumeric(movieId)) {
      return movieFilePath(Long.valueOf(movieId));
    }

    return Movies.findHlsPathByChannelId(request);
  }


  public File movieFilePath(Long movieId) {
    Movie movie = movieMapper.findByMovieId(movieId);
    String filePath = movie.getUrl();
    log.info(movieId.toString());
    log.info(filePath);

    return new File(filePath);
  }

  public void delete(long movieId) {
    movieMapper.delete(movieId);
  }

  public List<Movie> getLiveLists(long channelId) {
    return movieMapper.findByChannelId(channelId);
  }
}
