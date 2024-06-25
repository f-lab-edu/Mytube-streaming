package com.flab.Mytube.service;

import com.flab.Mytube.domain.LiveStreaming;
import com.flab.Mytube.domain.Movie;
import com.flab.Mytube.dto.movie.request.ChatJoinRequest;
import com.flab.Mytube.dto.movie.response.StartingShowResponse;
import com.flab.Mytube.mappers.LiveStreamingMapper;
import com.flab.Mytube.mappers.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LiveService {

  private final LiveStreamingMapper liveMapper;
  private final MovieMapper movieeMapper;

  @Transactional // 방송 예약하기
  public void saveReservation(LiveStreaming liveStreaming) {
    liveMapper.save(liveStreaming);
  }

  @Transactional // 라이브 시작
  public StartingShowResponse startShow(long liveId) {
    LiveStreaming result = liveMapper.findByLiveId(liveId);
    long movieId = result.getMovieId();
    Movie movie = movieeMapper.findByMovieId(movieId);
    StartingShowResponse response = StartingShowResponse.builder()
        .id(result.getId())
        .url(movie.getUrl())
        .title(result.getTitle())
        .contents(result.getContents())
        .userCount(result.getUserCount())
        .thumbsUp(result.getThumbsUp()).build();
    return response;
  }

  // TODO: 라이브 종료
  public void endLive(long id) {
    System.out.println(id + " 번 라이브가 종료되었습니다.");
  }

  public void delete(long id) {
    liveMapper.delete(id);
  }

  // TODO: 채팅
  public void requestJoin(ChatJoinRequest request) {
    System.out.println("채팅 참여!");
    System.out.println(request.toString());
  }
}
