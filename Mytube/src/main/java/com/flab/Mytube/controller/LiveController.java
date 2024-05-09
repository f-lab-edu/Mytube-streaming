package com.flab.Mytube.controller;

import com.flab.Mytube.dto.movie.request.ChatJoinRequest;
import com.flab.Mytube.service.LiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/live")
public class LiveController {
    private static LiveService liveService = new LiveService();
    @PostMapping("/{liveId}/chat")
    public void joinChat(@RequestBody ChatJoinRequest request){
        //라이브 채팅 참여
        liveService.requestJoin(request);
    }

    @PatchMapping("/{liveId}/end")
    public void endLive(@PathVariable("liveId") long liveId){
        //라이브 종료/(저장까지?)
        liveService.endLive(liveId);
    }

    @PutMapping("/{liveId/prefer")
    public void preferLive(@PathVariable("liveId") long liveId, @RequestBody long userId){
        //라이브 좋아요~
        liveService.prefer(liveId, userId);
    }
}
