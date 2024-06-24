package com.flab.Mytube.mappers;

import com.flab.Mytube.domain.Movie;
import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface MovieMapper {
  public void save(@Param("request") FileUploadRequest Movie);
  public void delete(@Param("movieId") long movieId);
  public Movie findByMovieId(@Param("movieId") long movieId);
  public List<Movie> findByChanelId(@Param("chanelId") long chanelId);
}
