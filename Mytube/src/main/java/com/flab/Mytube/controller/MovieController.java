package com.flab.Mytube.controller;

import com.flab.Mytube.dto.movie.request.InsertMovieRequest;
import com.flab.Mytube.dto.movie.request.ReserveShowRequest;
import com.flab.Mytube.dto.movie.request.JoinChatRequest;
import com.flab.Mytube.dto.movie.response.Response;
import com.flab.Mytube.dto.movie.response.StartingShowResponse;
import com.flab.Mytube.service.MovieService;
import com.flab.Mytube.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;
    private final StreamingService streamingService;

    @PostMapping("")
    public HttpStatus reserve(@RequestBody ReserveShowRequest request){
        // 관련 dto 생성하여 매개변수로 전달
        // live 예약하기
        Response resultNode = streamingService.reserveMovie(request);
        if(resultNode.getCode()==201){
            return HttpStatus.CREATED;
        }
        return HttpStatus.BAD_REQUEST;
    }
    @GetMapping("/{streaming_id}/start")
    public String startLive( @PathVariable("streaming_id") long streamingId){

        StartingShowResponse resultNode = streamingService.startShow(streamingId);
        return resultNode.toString();
    }

    @PostMapping("/upload")
    public long upload(@RequestBody InsertMovieRequest param){
        // 동영상 업로드
        return movieService.insertMovie(param).getId();
    }

    @PostMapping("/{movie_id}/chat")
    public BigInteger joinChat(@RequestBody JoinChatRequest param, @PathVariable("movie_id") BigInteger movie_id){
        //라이브 채팅 참여
        return streamingService.joinChat(param, movie_id).getId();
    }

    @PostMapping("/{movie_id}/store")
    public void storeMovie(){
        //라이브 저장하기
    }




}
