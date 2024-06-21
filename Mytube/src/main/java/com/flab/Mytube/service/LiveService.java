package com.flab.Mytube.service;

import com.flab.Mytube.dto.movie.request.ChatJoinRequest;
import com.flab.Mytube.dto.movie.request.ReserveShowRequest;
import com.flab.Mytube.dto.movie.response.StartingShowResponse;
import com.flab.Mytube.mapper.LiveMapper;
import com.flab.Mytube.vo.LiveStreamingVO;
import com.flab.Mytube.vo.MovieVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LiveService {

  private final LiveMapper liveMapper;

  @Transactional // 방송 예약하기
  public void reserveShow(ReserveShowRequest request) {
    liveMapper.reserveShow(request.makeReservation());
  }

  @Transactional // 라이브 시작
  public StartingShowResponse startShow(long liveId) {
    LiveStreamingVO result = liveMapper.findByStartingLiveId(liveId);
    long movieId = result.getMovieId();
    MovieVO movie = liveMapper.getMovieUri(movieId);
    StartingShowResponse response = StartingShowResponse.builder()
        .id(result.getId())
        .url(movie.getUrl())
        .title(result.getTitle())
        .contents(result.getContents())
        .userCount(result.getUserCount())
        .thumbsUp(result.getThumbsUp()).build();
    return response;
  }

  //라이브 종료
  public void endLive(long id) {
    System.out.println(id + " 번 라이브가 종료되었습니다.");
  }

  // 라이브 삭제
  public void delete(long id) {
  }

  // 채팅
  public void requestJoin(ChatJoinRequest request) {
    System.out.println("채팅 참여!");
    System.out.println(request.toString());
  }
}
