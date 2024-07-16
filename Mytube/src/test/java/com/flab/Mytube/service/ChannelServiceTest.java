package com.flab.Mytube.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.flab.Mytube.domain.LiveStreaming;
import com.flab.Mytube.domain.Movie;
import com.flab.Mytube.error.exceptions.ResourceNotFoundException;
import com.flab.Mytube.mappers.LiveStreamingMapper;
import com.flab.Mytube.mappers.MovieMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
  void init() {
    this.service = ChannelService.builder()
        .liveMapper(liveMapper)
        .movieMapper(movieMapper)
        .build();
  }


  @Test
  @DisplayName("유효한 LiveId:4 delete")
  void delete_valid() {
    long liveId = 4;
    service.delete(liveId);
  }

  @Test
  @DisplayName("존재하지 않는 LiveId:1 delete")
  void delete_invalid() {
    long liveId = 1;
    assertThrows(ResourceNotFoundException.class, () -> {
      service.delete(liveId);
    });
  }

  @Test
  @DisplayName("유효한 LiveId:21 replay")
  void replay_유효한_id_받았을_때() {
    long liveId = 21;
    Movie movie = service.replay(liveId);
    assertThat(movie.getChannelId()).isEqualTo(2L);
    assertThat(movie.getId()).isEqualTo(179L);
  }

  @Test
  @DisplayName("삭제된 LiveId:15 replay")
  void replay_삭제된_id_받았을_때() {
    long liveId = 15;
    assertThrows(ResourceNotFoundException.class, () -> {
      service.delete(liveId);
    });
  }

  @Test
  @DisplayName("유효한 userId:2 getLiveList")
  void getLiveList_valid() {
    long userId = 2;
    List<LiveStreaming> lists = service.getLiveList(userId);
  }

  @Test
  @DisplayName("삭제된 userId:150 getLiveList")
  void getLiveList_invalid() {
    long userId = 150;
    assertThrows(ResourceNotFoundException.class, () -> {
      service.getLiveList(userId);
    });
  }
}