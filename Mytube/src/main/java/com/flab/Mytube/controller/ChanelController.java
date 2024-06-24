package com.flab.Mytube.controller;

import com.flab.Mytube.domain.LiveStreaming;
import com.flab.Mytube.domain.Movie;
import com.flab.Mytube.service.ChanelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/chanels")
public class ChanelController {

  private final ChanelService service;

  //    현재 채널 라이브 및 동영상 목록 조회
  //    TODO: 조회하는 코드에서 deletedAt에 값이 있다면 불러오지 못하게 처리할 것
  @GetMapping("/{chanelId}")
  public List<LiveStreaming> getLiveList(@PathVariable("chanelId") long chanelId) {
    return service.getLiveList(chanelId);
  }

  //현재 채널 지난 라이브 다시보기
  @GetMapping("/lives/{liveId}/replay")
  public Movie replayLive(@PathVariable("liveId") long liveId) {
    return service.replay(liveId);
  }

//  @GetMapping("/{chanelId}/movies")
//  public List<Movie> getMovies(@PathVariable("chanelId") long chanelId) {
//    List<Movie> result = service.getUploadMovie(chanelId);
//    return result;
//  }
}
