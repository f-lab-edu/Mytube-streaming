package com.flab.Mytube.controller;

import com.flab.Mytube.dto.movie.request.ThumbsUpRequest;
import com.flab.Mytube.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

  private final UserService userService;

  // 좋아요 컨트롤러
  @PostMapping("/prefer/lives/{liveId}")
  public HttpStatus preferLive(@PathVariable("liveId") long liveId,
      @RequestBody ThumbsUpRequest request) {
    ThumbsUpRequest newRequest = new ThumbsUpRequest(liveId);
    userService.prefer(newRequest);
    return HttpStatus.CREATED;
  }
}
