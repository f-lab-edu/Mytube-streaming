package com.flab.Mytube.config;


import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FFmpegConfig {

  @Value("${ffmpeg.path:/usr/bin/ffmpeg}")
  private String ffmpegPath;

  @Value("${ffprobe.path:/usr/bin/ffprobe}")
  private String ffprobePath;

  @Bean
  public FFmpeg ffMpeg() throws IOException {
    return new FFmpeg(ffmpegPath);
  }

  @Bean
  public FFprobe ffProbe() throws IOException {
    return new FFprobe(ffprobePath);
  }
}
