package com.flab.Mytube.controller;

import com.flab.Mytube.dto.movie.request.ChatJoinRequest;
import com.flab.Mytube.dto.movie.request.ReserveShowRequest;
import com.flab.Mytube.dto.movie.response.Response;
import com.flab.Mytube.dto.movie.response.StartingShowResponse;
import com.flab.Mytube.service.LiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/lives")
public class LiveController {
    private final LiveService liveService;


    // 라이브 예약하기 요청
    @PostMapping("")
    public HttpStatus reserve(@RequestBody ReserveShowRequest request){
        Response resultNode = liveService.reserveShow(request);
        if(resultNode.getCode()==201){
            return HttpStatus.CREATED;
        }
        return HttpStatus.BAD_REQUEST;
    }

    // 라이브 시작 요청
    @GetMapping("/{liveId}/start")
    public StartingShowResponse startLive(@PathVariable("liveId") long liveId){
//        StartingShowResponse result = settingLiveService.startShow(liveId);
        StartingShowResponse result = liveService.startShow(liveId);
        return result;
    }

    //라이브 종료 (저장까지?)
    @PatchMapping("/{liveId}")
    public void endLive(@PathVariable("liveId") long liveId) {
        liveService.endLive(liveId);
    }


    //라이브 삭제
//    관련 라이브 테이블에서 deletedAt 날짜 추가하기
//    조회하는 코드에서 이 부분에 값이 있다면 불러오지 못하게 처리할 것

    @PatchMapping("/{chanelId}/movies/{id}")
    public void deleteLive(@PathVariable("chanelId") long chanelId, @PathVariable("id") long id) {
        // 초기에 생각할 때 streamerId, movieId 가 필요하지 않을까 햇는데...
//        생각해보니까 조회하면서 id 다 끌고와서 프론트에 id 가 있을텐데 받을 필요가 있을까?
        liveService.delete(id);
//        service.delete(id);
    }

    @PostMapping("/{liveId}")
    public void joinChat(@RequestBody ChatJoinRequest request) {
        //라이브 채팅 참여
        liveService.requestJoin(request);
    }
}
