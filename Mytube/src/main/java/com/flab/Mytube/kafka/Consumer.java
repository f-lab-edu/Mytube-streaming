package com.flab.Mytube.kafka;

import com.flab.Mytube.utils.Movies;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.progress.Progress;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Consumer {

  @KafkaListener(topics = "thing1", groupId = "myGroup", containerFactory = "kafkaListenerContainerFactory")
  public CompletableFuture<String> listen(String data) {
    CompletableFuture<String> future = new CompletableFuture<>();
    future.complete("done");
    return future;
  }

  private final FFmpeg fFmpeg;
  private final FFprobe fFprobe;
  private final Movies movie = new Movies();

  @KafkaListener(topics = "video-topic", groupId = "myGroup")
  public void listenGroupFoo(String message) {
    log.info("Received Message in group myGroup: " + message);
    // Kafka에서 받은 메시지를 사용하여 비디오 인코딩
    processVideoEncoding(message);
  }

  private void processVideoEncoding(String filePathStr) {
    Path filePath = Paths.get(filePathStr);
    String path = filePath.toString();
    String outPath = filePath.getParent().resolve("hls").toString(); // 저장 위치 생성
    File output = movie.resultFile(outPath);

    String fileName = filePath.getFileName().toString().split("\\.")[0];
    String tsName = fileName;

    // ts 파일로 분할 및 분해 설정
    String source = fileName + ".m3u8";
    FFmpegBuilder builder = movie.segmentationTs(source, path, output, tsName);

    // builder 실행
    run(builder);
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

}
