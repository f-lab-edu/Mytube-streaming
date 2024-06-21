package com.flab.Mytube.config;


import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FFmpegConfig {

  @Value("/opt/homebrew/Cellar/ffmpeg/7.0.1/bin/ffmpeg")
  private String ffmpegPath;

  @Value("/opt/homebrew/Cellar/ffmpeg/7.0.1/bin/ffmpeg")
  private String ffprobePath;

  //  설치된 파일(FFmpeg와 FFprobe)을 가져와서 객체를 초기화하고 사용 가능한지 확인하는 클래스입니다.
  @Bean
  public FFmpeg ffMpeg() throws IOException {
    return new FFmpeg(ffmpegPath);
  }

  @Bean
  public FFprobe ffProbe() throws IOException {
    return new FFprobe(ffprobePath);
  }
}
