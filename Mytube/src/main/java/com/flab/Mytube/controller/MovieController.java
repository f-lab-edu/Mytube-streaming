package com.flab.Mytube.controller;

import com.flab.Mytube.dto.movie.request.InsertMovieRequest;
import com.flab.Mytube.dto.movie.request.InsertPostRequest;
import com.flab.Mytube.dto.movie.request.JoinChatRequest;
import com.flab.Mytube.service.MovieService;
import com.flab.Mytube.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;
    private final StreamingService streamingService;

    @PostMapping("")
    public BigInteger reserve(@RequestBody InsertPostRequest.Param param){
        // 관련 dto 생성하여 매개변수로 전달
        // live 예약하기
        return streamingService.reserveMovie(param).getID();
    }
    @PostMapping("/upload")
    public BigInteger upload(@RequestBody InsertMovieRequest.Param param){
        // 동영상 업로드
        return movieService.insertMovie(param).getID();
    }

    @PostMapping("/{movie_id}/chat")
    public BigInteger joinChat(@RequestBody JoinChatRequest param, @PathVariable("movie_id") BigInteger movie_id){
        //라이브 채팅 참여
        return streamingService.joinChat(param, movie_id).getID();
    }

    @PostMapping("/{movie_id}/store")
    public void storeMovie(){
        //라이브 저장하기
    }




}
