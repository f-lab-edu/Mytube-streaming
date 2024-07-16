package com.flab.Mytube.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.flab.Mytube.domain.Movie;
import com.flab.Mytube.error.exceptions.ResourceNotFoundException;
import com.flab.Mytube.mappers.LiveStreamingMapper;
import com.flab.Mytube.mappers.MovieMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class ChannelServiceTest {

  @Autowired
  private LiveStreamingMapper liveMapper;

  @Autowired
  private MovieMapper movieMapper;

  ChannelService service;

  @BeforeEach
  void init(){
    this.service = ChannelService.builder()
        .liveMapper(liveMapper)
        .movieMapper(movieMapper)
        .build();
  }


  @Test
  void delete() {
  }

  @Test
  void replay_유효한_id_받았을_때() {
    long liveId = 21;
    Movie movie = service.replay(liveId);
    assertThat(movie.getChannelId()).isEqualTo(2L);
    assertThat(movie.getId()).isEqualTo(179L);
  }

  @Test
  void replay_삭제된_id_받았을_때() {
    long liveId = 15;
    assertThrows(ResourceNotFoundException.class, () -> {
      service.delete(liveId);
    });
  }

  @Test
  void getLiveList() {
  }
}