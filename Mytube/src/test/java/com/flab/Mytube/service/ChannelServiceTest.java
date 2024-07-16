package com.flab.Mytube.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;
import static org.junit.jupiter.api.Assertions.*;

import com.flab.Mytube.MytubeApplication;
import com.flab.Mytube.domain.LiveStreaming;
import com.flab.Mytube.domain.Movie;
import com.flab.Mytube.error.exceptions.ResourceNotFoundException;
import com.flab.Mytube.mappers.LiveStreamingMapper;
import com.flab.Mytube.mappers.UserMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

//@MybatisTest
//@RunWith(SpringRunner.class)
@MybatisTest
//@AutoConfigureMybatis // Specify instead of @MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = MytubeApplication.class)
@RunWith(MockitoJUnitRunner.class)
class ChannelServiceTest {

  @InjectMocks
  private ChannelService service;

  @Mock
  private LiveStreamingMapper liveMapper;

  @Test
  void delete() {
  }

  @Test
  void replay_유효한_id_받았을_때() {
    long liveId = 21;
    LiveStreaming live = liveMapper.findByLiveId(liveId);
    assertThat(live.getChannelId()).isEqualTo(2L);
    assertThat(live.getMovieId()).isEqualTo(179L);
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