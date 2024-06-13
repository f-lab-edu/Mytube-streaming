package com.flab.Mytube.controller;

import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import com.flab.Mytube.service.ConvertMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final ConvertMovieService convertMovieService;

    // 동영상 업로드 요청
    @PostMapping("")
    public HttpStatus upload(@RequestParam("movie") MultipartFile file, @RequestParam("chanelId") long chanelId, @RequestParam("subject") String subject){
        // 동영상 업로드
        FileUploadRequest request = new FileUploadRequest(file, chanelId, subject);
        HttpStatus response = convertMovieService.uploadMovie(request);
        return response;
    }

//    동영상 정보 요청
    @GetMapping("/{movieId}")
    public void getMovie(@PathVariable("movieId") long movieId){
        System.out.println(movieId);
    }

//    동영상 삭제
    @PatchMapping("/{movieId}")
    public void deleteMovie(@PathVariable("{movieId}") long movieId){

    }

//
//
//    @GetMapping("/{streamerId}")
//    public List<MovieVO> getMovies( @PathVariable("streamerId") long streamerId){
//        List<MovieVO> result = settingLiveService.getUploadMovie(streamerId);
//
////        StartingShowResponse resultNode = settingLiveService.startShow(streamingId);
////        return resultNode.toString();
//        return result;
//    }
}
