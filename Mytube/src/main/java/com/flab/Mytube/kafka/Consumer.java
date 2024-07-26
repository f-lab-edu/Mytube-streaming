package com.flab.Mytube.kafka;

import com.flab.Mytube.dto.movie.request.ChuncksBuildRequest;
import com.flab.Mytube.utils.MoviePath;
import com.flab.Mytube.utils.Movies;
import java.io.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer implements MessageListener<String, String> {

  private final FFmpeg fFmpeg;
  private final FFprobe fFprobe;
  private final MoviePath moviePath;

  @Override
  @KafkaListener(topics = "videoPath", groupId = "myGroup", containerFactory = "kafkaListenerContainerFactory")
  public void onMessage(ConsumerRecord<String, String> data) {
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
}
