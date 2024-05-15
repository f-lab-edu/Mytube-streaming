package com.flab.Mytube.controller;

import com.flab.Mytube.service.ReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/chanel")
public class ReadController {
    ReadService service = new ReadService();
    //라이브 삭제
//    관련 라이브 테이블에서 deletedAt 날짜 추가하기
//    조회하는 코드에서 이 부분에 값이 있다면 불러오지 못하게 처리할 것
    @PatchMapping("/{streamerId}/movie/{movieId}")
    public void deleteLive(@PathVariable("streamerId") long streamerId, @PathVariable("movieId") long movieId){

    }

    //현재 채널 지난 라이브 다시보기
    //    조회하는 코드에서 deletedAt에 값이 있다면 불러오지 못하게 처리할 것
    @GetMapping("/{streamerId}/movie/{movieId}/replay")
    public void replayLive(@PathVariable("streamerId") long streamerId, @PathVariable("movieId") long movieId){

    }

//    현재 채널 라이브 및 동영상 목록 조회
//    조회하는 코드에서 deletedAt에 값이 있다면 불러오지 못하게 처리할 것
    @GetMapping("/{streamerId}")
    public void getLiveList(@PathVariable("streamerId") long streamerId){

    }
}
