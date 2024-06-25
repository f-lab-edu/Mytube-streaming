package com.flab.Mytube.service;

import com.flab.Mytube.domain.LiveStreaming;
import com.flab.Mytube.domain.Movie;
import com.flab.Mytube.error.exceptions.ResourceNotFoundException;
import com.flab.Mytube.mappers.LiveStreamingMapper;
import com.flab.Mytube.mappers.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChannelService {

  @Autowired
  private final LiveStreamingMapper liveMapper;
  @Autowired
  private final MovieMapper movieMapper;


  public void delete(long liveId) {
    LiveStreaming live = liveMapper.findByLiveId(liveId);
    if (live == null) {
      throw new ResourceNotFoundException("요청하신 라이브를 찾을 수 없습니다.");
    }
    liveMapper.delete(liveId);
  }


  // 지난 라이브 조회
//  TODO: 라이브 조회할 대 liveId, channelId 모두 사용해서 탐색하도록?
  public Movie replay(long liveId) {
    LiveStreaming live = liveMapper.findByLiveId(liveId);
    if (live == null) {
      throw new ResourceNotFoundException("요청하신 라이브를 찾을 수 없습니다.");
    }
    long movieId = live.getMovieId();
    if (movieId == 0) {
      throw new ResourceNotFoundException("요청하신 라이브를 찾을 수 없습니다.");
    }
    return movieMapper.findByMovieId(movieId);
  }

  //    현재 채널 라이브 및 동영상 목록 조회
  public List<LiveStreaming> getLiveList(long userId) {
    List<LiveStreaming> lists = liveMapper.findByChannelId(userId);

    if(lists.size()==0){
      throw new ResourceNotFoundException(userId+"님이 진행했던 Live 를 찾을 수 없습니다.");
    }
    return lists;
  }
}
