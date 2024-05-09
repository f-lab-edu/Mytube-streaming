package com.flab.Mytube.controller;

import com.flab.Mytube.dto.movie.request.UploadMovieRequest;
import com.flab.Mytube.dto.movie.request.ReserveShowRequest;
import com.flab.Mytube.dto.movie.response.Response;
import com.flab.Mytube.dto.movie.response.StartingShowResponse;
import com.flab.Mytube.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final StreamingService streamingService;

    // 동영상 업로드 요청
    @PostMapping("/upload")
    public HttpStatus upload(@RequestBody UploadMovieRequest request){
        // 동영상 업로드
        Response resultNode =streamingService.insertMovie(request);
        if(resultNode.getCode()==201){
            return HttpStatus.CREATED;
        }
        return HttpStatus.BAD_REQUEST;
    }

    // 라이브 예약하기 요청
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

    // 라이브 시작 요청
    @GetMapping("/{streamingId}/start")
    public String startLive( @PathVariable("streamingId") long streamingId){
        StartingShowResponse resultNode = streamingService.startShow(streamingId);
        return resultNode.toString();
    }
}
