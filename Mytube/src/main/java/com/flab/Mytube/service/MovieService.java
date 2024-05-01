package com.flab.Mytube.service;

import com.flab.Mytube.movie.request.InsertMovieRequest;
import com.flab.Mytube.movie.response.InsertMovieResponse;
import com.flab.Mytube.mapper.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieMapper movieMapper;

    @Transactional
    public InsertMovieResponse insertMovie(InsertMovieRequest param){
        long resultID = movieMapper.addMovie(param);
        return new InsertMovieResponse(param.getID(), 201, "success!");
    }
}
