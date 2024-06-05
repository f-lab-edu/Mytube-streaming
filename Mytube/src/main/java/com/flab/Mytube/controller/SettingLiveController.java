package com.flab.Mytube.controller;

import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import com.flab.Mytube.dto.movie.request.UploadMovieRequest;
import com.flab.Mytube.dto.movie.request.ReserveShowRequest;
import com.flab.Mytube.dto.movie.response.Response;
import com.flab.Mytube.dto.movie.response.StartingShowResponse;
import com.flab.Mytube.service.SettingLiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class SettingLiveController {
    private final SettingLiveService settingLiveService;

    // 동영상 업로드 요청
    @PostMapping("/upload")
    public HttpStatus upload(@RequestParam("movie") MultipartFile file, @RequestParam("streamerId") long streamerId, @RequestParam("subject") String subject){
        // 동영상 업로드
        FileUploadRequest request = new FileUploadRequest(file, streamerId, subject);
        settingLiveService.movieBuilder(request);
//        HttpStatus response = settingLiveService.insertMovie(request);
        return HttpStatus.CREATED;
//        return response;
    }

    // 라이브 예약하기 요청
    @PostMapping("")
    public HttpStatus reserve(@RequestBody ReserveShowRequest request){
        // 관련 dto 생성하여 매개변수로 전달
        // live 예약하기
        Response resultNode = settingLiveService.reserveMovie(request);
        if(resultNode.getCode()==201){
            return HttpStatus.CREATED;
        }
        return HttpStatus.BAD_REQUEST;
    }

    // 라이브 시작 요청
    @GetMapping("/{streamingId}/start")
    public String startLive( @PathVariable("streamingId") long streamingId){
        StartingShowResponse resultNode = settingLiveService.startShow(streamingId);
        return resultNode.toString();
    }
}
