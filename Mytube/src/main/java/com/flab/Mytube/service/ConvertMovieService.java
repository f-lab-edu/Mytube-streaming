package com.flab.Mytube.service;

import com.flab.Mytube.domain.Movie;
import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import com.flab.Mytube.dto.movie.request.MovieDtailRequest;
import com.flab.Mytube.error.exceptions.NoDataSubmitException;
import com.flab.Mytube.kafka.Producer;
import com.flab.Mytube.mappers.MovieMapper;
import com.flab.Mytube.utils.MoviePath;
import com.flab.Mytube.utils.Movies;
import com.flab.Mytube.utils.Validations;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
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
  private final MoviePath moviePath;

  @Value("${kafka.encoding.topic}")
  String TOPIC;

  @Autowired
  Producer producer;

  @Autowired
  private KafkaTemplate<String, String> template;

  @Transactional
  public void uploadMovie(FileUploadRequest request) {
    if (request.isEmptyFile()) {
      throw new NoDataSubmitException("파일을 제출하지 않았습니다.");
    }

    String data = copyVideo(request);
    String key = request.createKey();
    request.addPath(MoviePath.chunkPathStr(data));
    movieMapper.save(request);

    sendToKafka(data, key);

//    String data = copyVideo(request);
//    String key = request.createKey();
//    moviePath.chunckPath(data);
//    producer.send(TOPIC, key, data);
//    request.addPath(MoviePath.chunkPathStr(data));
//    movieMapper.save(request);
  }

//  @Transactional
  public void sendToKafka(String data, String key){
    moviePath.chunckPath(data);

    try{
      template.send(TOPIC, key, data).get(10, TimeUnit.SECONDS);
    }
    catch (ExecutionException e) {
      log.info("[ERROR] ExecutionException occur");
    }
    catch (TimeoutException | InterruptedException e) {
      log.info("[ERROR] ExeTimeoutException occur ");
    }
  }

  public String copyVideo(FileUploadRequest request){
    String fileName = request.getOriginFileName();
    Path originPath = moviePath.originRootPath(request);
    originPath = originPath.resolve(fileName);

    try (OutputStream os = Files.newOutputStream(originPath)) {
      byte[] bytes = request.getFile().getBytes();
      Files.write(originPath, bytes);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return originPath.toString();
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
