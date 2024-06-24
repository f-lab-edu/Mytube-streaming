package com.flab.Mytube.service;

import com.flab.Mytube.dao.LivePageDAO;
import com.flab.Mytube.error.exceptions.ResourceNotFoundException;
import com.flab.Mytube.mapper.ReadMapper;
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
  private final ReadMapper readMapper;


  public void delete(long liveId) {
    LiveStreamingVO live = readMapper.getLiveContents(liveId);
    if (live == null) {
      throw new ResourceNotFoundException("요청하신 라이브를 찾을 수 없습니다.");
    }
    readMapper.liveDelete(liveId);
  }


  // 지난 라이브 조회
//  TODO: 라이브 조회할 대 liveId, chanelId 모두 사용해서 탐색하도록
  public MovieVO replay(long liveId, long chanelId) {
    LiveStreamingVO live = readMapper.getLiveContents(liveId);
    if (live == null || live.isEmpty()) {
      throw new ResourceNotFoundException("요청하신 라이브를 찾을 수 없습니다.");
    }
    long movieId = live.getMovieId();
    if (movieId == 0) {
      throw new ResourceNotFoundException("요청하신 라이브를 찾을 수 없습니다.");
    }
    return readMapper.getMovieUrl(movieId);
  }

  //    현재 채널 라이브 및 동영상 목록 조회
//    TODO: 조회하는 코드에서 deletedAt에 값이 있다면 불러오지 못하게 처리할 것
//    https://authorkim0921.tistory.com/7 (참고)
  public List<LivePageDAO> getLiveList(long userId) {
    List<LivePageDAO> lists = readMapper.getLiveList(userId);
    if(lists.size()==0){
      throw new ResourceNotFoundException(userId+"님이 진행했던 Live 를 찾을 수 없습니다.");
    }
    return lists;
  }

  // 업로드한 동영상 목록 조회
  @Transactional
  public List<MovieVO> getUploadMovie(long chanelId) {
    // sreamerId 와 연관된 동영상 반환해오기
    List<MovieVO> result = readMapper.uploadMovieList(chanelId);
    if(result==null||result.isEmpty()){
      throw new ResourceNotFoundException(chanelId+"님이 업로드한 영상을 불러오지 못했습니다.");
    }
    return result;
  }
}
