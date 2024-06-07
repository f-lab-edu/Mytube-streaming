package com.flab.Mytube.controller;

import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import com.flab.Mytube.dto.movie.request.UploadMovieRequest;
import com.flab.Mytube.dto.movie.request.ReserveShowRequest;
import com.flab.Mytube.dto.movie.response.Response;
import com.flab.Mytube.dto.movie.response.StartingShowResponse;
import com.flab.Mytube.service.SettingLiveService;
import com.flab.Mytube.vo.MovieVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
        HttpStatus response = settingLiveService.insertMovie(request);
        return response;
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

    // 라이브 시작 요청 : TODO: API 명세 수정 필요할 듯. 이름이 헷갈려!!!
    @GetMapping("/{streamingId}/start")
    public StartingShowResponse startLive( @PathVariable("streamingId") long streamingId){
        StartingShowResponse result = settingLiveService.startShow(streamingId);
        return result;
    }

    @GetMapping("/{streamerId}")
    public List<MovieVO> getMovies( @PathVariable("streamerId") long streamerId){
        List<MovieVO> result = settingLiveService.getUploadMovie(streamerId);

//        StartingShowResponse resultNode = settingLiveService.startShow(streamingId);
//        return resultNode.toString();
        return result;
    }
}
