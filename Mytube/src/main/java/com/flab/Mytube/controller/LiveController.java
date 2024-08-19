package com.flab.Mytube.controller;

import com.flab.Mytube.domain.LiveStreaming;
import com.flab.Mytube.dto.movie.request.ChatJoinRequest;
import com.flab.Mytube.dto.movie.request.WatchLiveRequest;
import com.flab.Mytube.dto.movie.response.StartingShowResponse;
import com.flab.Mytube.service.LiveService;
import com.flab.Mytube.service.LiveStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/lives")
public class LiveController {

  private final LiveService liveService;
  private final LiveStatusService statusService;


  @PostMapping("")
  public void reserve(@RequestBody LiveStreaming request) {
    liveService.saveReservation(request);
  }

  @GetMapping("/{liveId}/start")
  public StartingShowResponse startLive(@PathVariable("liveId") long liveId) {
    StartingShowResponse result = liveService.startShow(liveId);
    statusService.startLive(liveId, result.getUrl());
    return result;
  }

  @PatchMapping("/{liveId}/{processTime}")
  public void processLive(@PathVariable("liveId") long liveId,
      @PathVariable("processTime") LocalTime time) {
    statusService.currentLive(liveId, time);
  }

  @PatchMapping("/{liveId}")
  public void endLive(@PathVariable("liveId") long liveId) {
    liveService.endLive(liveId);
    statusService.endLive(liveId);
  }

  @GetMapping("/{liveId}/{channelId}")
  public ResponseEntity<InputStreamResource> getMovie(
      @PathVariable("liveId") int liveId,
      @PathVariable("channelId") String channelId
  ) {
    WatchLiveRequest request = WatchLiveRequest.builder()
        .channelId(channelId)
        .liveId(liveId)
        .build();
    File liveSource = statusService.joinLive(request);
    try {
      InputStreamResource resource = new InputStreamResource(new FileInputStream(liveSource));
      return ResponseEntity.ok()
          .contentType(MediaType.parseMediaType("application/x-mpegURL"))
          .body(resource);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  // TODO: 권한이 있는 사용자가 요청을 했는지 확인, 검증
  @PatchMapping("/{channelId}/lives/{liveId}")
  public void deleteLive(@PathVariable("channelId") long channelId,
      @PathVariable("liveId") long liveId) {
    liveService.delete(liveId);
  }

  @PostMapping("/{liveId}")
  public void joinChat(@RequestBody ChatJoinRequest request) {
    // TODO: 라이브 채팅 참여
    liveService.requestJoin(request);
  }
}
