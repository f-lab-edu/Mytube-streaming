package com.flab.Mytube.controller;

import com.flab.Mytube.domain.Movie;
import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import com.flab.Mytube.dto.movie.request.MovieDtailRequest;
import com.flab.Mytube.service.ConvertMovieService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
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

  // 동영상 업로드 요청 TODO: 올바른 파일 형식인지 확인 (ex. 사진이라면? 문서파일이라면?)
  @PostMapping("")
  public void upload(@RequestParam("movie") MultipartFile file,
      @RequestParam("channelId") long channelId) {
    FileUploadRequest request = FileUploadRequest.builder()
        .file(file)
        .channelId(channelId)
        .build();
    request.addSubject();
    convertMovieService.uploadMovie(request);
  }

  // 스트리밍 시작을 위한 동영상 정보(m3m8, ts file) 요청
  @GetMapping("/{movieId}/channels/{channelId}")
  public ResponseEntity<InputStreamResource> getMovie(
      @PathVariable("channelId") int channelId,
      @PathVariable("movieId") String movieId
  ) {
    MovieDtailRequest movie = MovieDtailRequest.builder()
        .movieId(movieId)
        .channel(channelId)
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

  @PatchMapping("/{movieId}")
  public void deleteMovie(@PathVariable("movieId") long movieId) {
    convertMovieService.delete(movieId);
  }

  @GetMapping("/channels/{channelId}")
  public List<Movie> MovieList(@PathVariable("channelId") long channelId){
    return convertMovieService.getLiveLists(channelId);
  }
}
