package com.flab.Mytube.controller;

import com.flab.Mytube.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    @PostMapping("")
    public void reserve(){
        // 관련 dto 생성하여 매개변수로 전달
        // live 예약하기
    }
    @PostMapping("/upload")
    public void upload(){
        // 동영상 업로드
    }

    @PostMapping("/{movie_id}/chat")
    public void joinChat(){
        //라이브 채팅 참여
    }

    @PostMapping("/{movie_id}/store")
    public void storeMovie(){
        //라이브 저장하기
    }




}
