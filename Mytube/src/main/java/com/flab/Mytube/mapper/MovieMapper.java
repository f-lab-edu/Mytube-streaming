package com.flab.Mytube.mapper;

import com.flab.Mytube.movie.request.InsertMovieRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface MovieMapper {
    long addMovie(@Param("param")InsertMovieRequest param);
}
