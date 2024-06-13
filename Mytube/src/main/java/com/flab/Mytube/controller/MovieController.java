package com.flab.Mytube.controller;

import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import com.flab.Mytube.dto.movie.request.MovieDtailRequest;
import com.flab.Mytube.service.ConvertMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final ConvertMovieService convertMovieService;

    // 동영상 업로드 요청
    @PostMapping("")
    public HttpStatus upload(@RequestParam("movie") MultipartFile file, @RequestParam("chanelId") long chanelId) {
        // 동영상 업로드
//        FileUploadRequest request = new FileUploadRequest(file, chanelId);
        FileUploadRequest request = FileUploadRequest.builder()
                .file(file)
                .chanelId(chanelId)
                .build();
        request.addSubject();
        HttpStatus response = convertMovieService.uploadMovie(request);
        return response;
    }

    //    동영상 정보 요청
//    @GetMapping("/{movieId}")
//    public ResponseEntity<InputStreamResource> getMovie(@PathVariable("movieId") long movieId) {
//        File liveSource = convertMovieService.getLiveFile(movieId);
//
//        try{
//            InputStreamResource resource = new InputStreamResource(new FileInputStream(liveSource));
//            return ResponseEntity.ok()
//                    .contentType(MediaType.parseMediaType("application/x-mpegURL"))
//                    .body(resource);
//        } catch (FileNotFoundException e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//    동영상 정보 요청

    // 스트리밍 시작을 위한 동영상 정보(m3m8, ts file) 요청
    @GetMapping("/chanel/{chanelId}/{fileId}")
    public ResponseEntity<InputStreamResource> getMovie(
            @PathVariable("chanelId") int chanelId,
            @PathVariable("fileId") String fileId
            ) {
        MovieDtailRequest movie = MovieDtailRequest.builder()
                .fileName(fileId)
                .chanelId(chanelId)
                .build();
        File liveSource = convertMovieService.getLiveFile(movie);

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

    //    동영상 삭제
    @PatchMapping("/{movieId}")
    public void deleteMovie(@PathVariable("{movieId}") long movieId) {

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
