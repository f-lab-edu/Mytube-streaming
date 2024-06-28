package com.flab.Mytube.controller;

import com.flab.Mytube.domain.LiveStreaming;
import com.flab.Mytube.domain.Movie;
import com.flab.Mytube.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/channels")
public class ChannelController {

  private final ChannelService service;

  //    현재 채널 라이브 및 동영상 목록 조회
  @GetMapping("/{channelId}")
  public List<LiveStreaming> getLiveList(@PathVariable("channelId") long channelId) {
    return service.getLiveList(channelId);
  }

  //현재 채널 지난 라이브 다시보기
  @GetMapping("/lives/{liveId}/replay")
  public Movie replayLive(@PathVariable("liveId") long liveId) {
    return service.replay(liveId);
  }
}