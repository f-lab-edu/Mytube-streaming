package com.flab.Mytube.service;

import com.flab.Mytube.dao.LivePageDAO;
import com.flab.Mytube.domain.LiveStreaming;
import com.flab.Mytube.domain.Movie;
import com.flab.Mytube.error.exceptions.ResourceNotFoundException;
import com.flab.Mytube.mappers.LiveStreamingMapper;
import com.flab.Mytube.mappers.MovieMapper;
import com.flab.Mytube.vo.LiveStreamingVO;
import com.flab.Mytube.vo.MovieVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChanelService {

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
//  TODO: 라이브 조회할 대 liveId, chanelId 모두 사용해서 탐색하도록
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
    List<LiveStreaming> lists = liveMapper.findByChanelId(userId);

    if(lists.size()==0){
      throw new ResourceNotFoundException(userId+"님이 진행했던 Live 를 찾을 수 없습니다.");
    }
    return lists;
  }

  // 업로드한 동영상 목록 조회
//  @Transactional
//  public List<MovieVO> getUploadMovie(long chanelId) {
//    // sreamerId 와 연관된 동영상 반환해오기
//    List<MovieVO> result = liveMapper.uploadMovieList(chanelId);
//    if(result==null||result.isEmpty()){
//      throw new ResourceNotFoundException(chanelId+"님이 업로드한 영상을 불러오지 못했습니다.");
//    }
//    return result;
//  }
}
