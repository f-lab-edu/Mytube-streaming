package com.flab.Mytube.service;

import com.flab.Mytube.dto.movies.request.UploadMovieRequest;
import com.flab.Mytube.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }
    public void addSchedule(){}
    public BigInteger addMovie(UploadMovieRequest request){
        return movieRepository.save(request);
    }

}
