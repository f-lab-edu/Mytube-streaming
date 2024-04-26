package com.flab.Mytube.repository;

import com.flab.Mytube.dto.movies.request.UploadMovieRequest;
import com.flab.Mytube.notices.vo.Movie;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

//@Mapper
@Repository
public interface MovieRepository {
    BigInteger save(@Param("movie") UploadMovieRequest movie);
    List<Movie> findAll();
    Movie findOne(@Param("movie") BigInteger id);
    void updateMovie(@Param("movie") Movie movie);
    void deletedById(@Param("movie") BigInteger id);
}
