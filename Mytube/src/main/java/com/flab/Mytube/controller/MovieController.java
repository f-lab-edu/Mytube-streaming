package com.flab.Mytube.controller;

import com.flab.Mytube.domain.Movie;
import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import com.flab.Mytube.dto.movie.request.MovieDtailRequest;
import com.flab.Mytube.error.exceptions.ResourceNotFoundException;
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

  @PostMapping("")
  public void upload(@RequestParam(value="movie") MultipartFile file,
      @RequestParam("channelId") long channelId) {
    FileUploadRequest request = FileUploadRequest.builder()
        .file(file)
        .channelId(channelId)
        .build();
    convertMovieService.uploadMovie(request);
  }

  @PatchMapping("/{movieId}")
  public void deleteMovie(@PathVariable("movieId") long movieId) {
    convertMovieService.delete(movieId);
  }

  @GetMapping("/channels/{channelId}")
  public List<Movie> MovieList(@PathVariable("channelId") long channelId) {
    return convertMovieService.getLiveLists(channelId);
  }
}
