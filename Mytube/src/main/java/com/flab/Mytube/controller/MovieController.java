package com.flab.Mytube.controller;

import com.flab.Mytube.dto.movies.request.ReserveMovieRequest;
import com.flab.Mytube.dto.movies.request.UploadMovieRequest;
import com.flab.Mytube.dto.movies.response.UploadMovieResponse;
import com.flab.Mytube.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

//@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;
    public MovieController(MovieService movieService){this.movieService = movieService;}


    // UploadMovieRequest >>>   streamerId  subject  url
    @PostMapping("/upload")
    public UploadMovieResponse upload(@RequestBody UploadMovieRequest request){
        // 동영상 업로드
        BigInteger movieId = movieService.addMovie(request);
        return new UploadMovieResponse(201, movieId, "Success");
    }
    @PostMapping("")
    public void reserve(@RequestBody ReserveMovieRequest request){
        // 관련 dto 생성하여 매개변수로 전달
        // live 예약하기
//        return movieService.method(request);
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
