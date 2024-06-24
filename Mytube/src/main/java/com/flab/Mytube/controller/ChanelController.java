package com.flab.Mytube.controller;

import com.flab.Mytube.dao.LivePageDAO;
import com.flab.Mytube.service.ChanelService;
import com.flab.Mytube.vo.MovieVO;
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
  public List<LivePageDAO> getLiveList(@PathVariable("chanelId") long chanelId) {
    return service.getLiveList(chanelId);
  }

  //현재 채널 지난 라이브 다시보기
  @GetMapping("/{chanelId}/lives/{liveId}/replay")
  public MovieVO replayLive(@PathVariable("chanelId") long chanelId,
      @PathVariable("liveId") long liveId) {
    return service.replay(liveId, chanelId);
  }

  @GetMapping("/{chanelId}/movies")
  public List<MovieVO> getMovies(@PathVariable("chanelId") long chanelId) {
    List<MovieVO> result = service.getUploadMovie(chanelId);
    return result;
  }
}
