package com.flab.Mytube.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;
import static org.junit.jupiter.api.Assertions.*;

import com.flab.Mytube.MytubeApplication;
import com.flab.Mytube.domain.LiveStreaming;
import com.flab.Mytube.domain.Movie;
import com.flab.Mytube.error.exceptions.ResourceNotFoundException;
import com.flab.Mytube.mappers.LiveStreamingMapper;
import com.flab.Mytube.mappers.MovieMapper;
import com.flab.Mytube.mappers.UserMapper;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

//@MybatisTest
//@RunWith(SpringRunner.class)
//@ExtendWith(MockitoExtension.class)
//@AutoConfigureMybatis // Specify instead of @MybatisTest
//@ContextConfiguration(classes = MytubeApplication.class)
//@RunWith(MockitoJUnitRunner.class)
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
//    LiveStreaming live = liveMapper.findByLiveId(liveId);
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