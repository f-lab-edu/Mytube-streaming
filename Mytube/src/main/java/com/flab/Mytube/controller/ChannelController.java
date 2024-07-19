package com.flab.Mytube.controller;

import com.flab.Mytube.domain.LiveStreaming;
import com.flab.Mytube.domain.Movie;
import com.flab.Mytube.dto.movie.request.MovieDtailRequest;
import com.flab.Mytube.error.exceptions.ResourceNotFoundException;
import com.flab.Mytube.service.ChannelService;
import com.flab.Mytube.service.ConvertMovieService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
  private final ConvertMovieService movieService;

  @GetMapping("/{channelId}")
  public List<LiveStreaming> getLiveList(@PathVariable("channelId") long channelId) {
    return service.getLiveList(channelId);
  }

  @GetMapping("/lives/{liveId}/replay")
  public Movie replayLive(@PathVariable("liveId") long liveId) {
    // TODO: null 이 들어올 때
    return service.replay(liveId);
  }


  @GetMapping("/{channelId}/movieId/{movieId}")
  public ResponseEntity<InputStreamResource> getMovie(@PathVariable("channelId") int channelId,
      @PathVariable("movieId") String movieId) {
    MovieDtailRequest movie = MovieDtailRequest.builder()
        .movieId(movieId)
        .channel(channelId)
        .build();
    File liveSource = movieService.movieFilePath(movie);

    try {
      InputStreamResource resource = new InputStreamResource(new FileInputStream(liveSource));
      return ResponseEntity.ok()
          .contentType(MediaType.parseMediaType("application/x-mpegURL"))
          .body(resource);
    } catch (FileNotFoundException e) {
      throw new ResourceNotFoundException("데이터를 불러오지 못했습니다.");
    }
  }
}
