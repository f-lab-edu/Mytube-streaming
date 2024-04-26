package com.flab.Mytube.service;

import com.flab.Mytube.dto.movie.InsertMovieRequest;
import com.flab.Mytube.dto.movie.response.InsertMovieResponse;
import com.flab.Mytube.mapper.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieMapper movieMapper;

    @Transactional
    public InsertMovieResponse insertMovie(InsertMovieRequest.Param param){
        BigInteger resultID = movieMapper.addMovie(param);
        return new InsertMovieResponse(resultID, 200, "success!");
    }
}
