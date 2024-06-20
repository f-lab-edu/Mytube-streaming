package com.flab.Mytube.controller;

import com.flab.Mytube.dto.movie.request.ChatJoinRequest;
import com.flab.Mytube.dto.movie.request.MovieDtailRequest;
import com.flab.Mytube.dto.movie.request.ReserveShowRequest;
import com.flab.Mytube.dto.movie.request.WatchLiveRequest;
import com.flab.Mytube.dto.movie.response.Response;
import com.flab.Mytube.dto.movie.response.StartingShowResponse;
import com.flab.Mytube.service.LiveService;
import com.flab.Mytube.service.LiveStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/lives")
public class LiveController {
    private final LiveService liveService;
    private final LiveStatusService statusService;


    // 라이브 예약하기 요청
    @PostMapping("")
    public void reserve(@RequestBody ReserveShowRequest request){
        liveService.reserveShow(request);
    }

    // 라이브 시작 요청
    @GetMapping("/{liveId}/start")
    public StartingShowResponse startLive(@PathVariable("liveId") long liveId){
        StartingShowResponse result = liveService.startShow(liveId);
        statusService.startLive(liveId, result.getUrl());
        return result;
    }

    // 라이브 진행도 반영
    @PatchMapping("/{liveId}/{processTime}")
    public void processLive(@PathVariable("liveId") long liveId, @PathVariable("processTime") LocalTime time){
        statusService.currentLive(liveId, time);
    }

    //라이브 종료 (저장까지?)
    @PatchMapping("/{liveId}")
    public void endLive(@PathVariable("liveId") long liveId) {
        liveService.endLive(liveId);
        statusService.stopLive(liveId);
        // 라이브 저장
        // 해당 실시간 라이브 접근 못하도록 제한, 다시보기로만 접근 하도록
//        statusService.liveEnd(liveId);
    }

    @GetMapping("/{liveId}/{chanelId}")
    public ResponseEntity<InputStreamResource> getMovie(
            @PathVariable("chanelId") String chanelId,
            @PathVariable("liveId") int liveId
    ) {
        WatchLiveRequest request = WatchLiveRequest.builder()
                .chanelId(chanelId)
                .liveId(liveId)
                .build();
        File liveSource = statusService.joinLive(request); // service 에서 파일 불러오기
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
//    @GetMapping("/{liveId}") // doing
//    public ResponseEntity<InputStreamResource> watchLive(@PathVariable("liveId") String liveId){
//        // 라이브 시청하기
//        File liveSource = statusService.joinLive(liveId);
//        System.out.println(liveSource.getPath());
//        if (liveSource == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        try {
//            InputStreamResource resource = new InputStreamResource(new FileInputStream(liveSource));
//            System.out.println(liveSource.getName());
//            return ResponseEntity.ok()
//                    .contentType(MediaType.parseMediaType("application/x-mpegURL"))
//                    .body(resource);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).build();
//        }
//    }


    // TODO: 라이브 삭제
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
        // TODO: 라이브 채팅 참여
        liveService.requestJoin(request);
    }
}
