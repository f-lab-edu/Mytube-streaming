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
    public void storeMovie(){
        //라이브 종료
    }
}
