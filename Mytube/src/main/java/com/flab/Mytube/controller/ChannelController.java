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

  @GetMapping("/{channelId}")
  public List<LiveStreaming> getLiveList(@PathVariable("channelId") long channelId) {
    return service.getLiveList(channelId);
  }

  @GetMapping("/lives/{liveId}/replay")
  public Movie replayLive(@PathVariable("liveId") long liveId) {
    // TODO: null 이 들어올 때
    return service.replay(liveId);
  }
}
