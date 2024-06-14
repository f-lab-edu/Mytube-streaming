package com.flab.Mytube.controller;

import com.flab.Mytube.dto.movie.request.ChatJoinRequest;
import com.flab.Mytube.dto.movie.request.ThumbsUpRequest;
import com.flab.Mytube.service.LiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/live")
public class LiveController {
    private final LiveService liveService;

    @PostMapping("/{liveId}/chat")
    public void joinChat(@RequestBody ChatJoinRequest request) {
        //라이브 채팅 참여
        liveService.requestJoin(request);
    }

    @PatchMapping("/{liveId}/end")
    public void endLive(@PathVariable("liveId") long liveId) {
        //라이브 종료/(저장까지?)s
        liveService.endLive(liveId);
    }

    // 좋아요 컨트롤러
    @PostMapping("/{liveId}/prefer")
    public HttpStatus preferLive(@PathVariable("liveId") long liveId, @RequestBody ThumbsUpRequest request) {
        ThumbsUpRequest newRequest= request.toBuilder()
                .liveId(liveId)
                .build();
        liveService.prefer(newRequest);
        return HttpStatus.CREATED;
    }
}
