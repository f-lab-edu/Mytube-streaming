package com.flab.Mytube.mappers;

import com.flab.Mytube.domain.Movie;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieMapper {
  public void save(Movie Movie);
  public void delete(long movieId);
  public Movie findByMovieId(long movieId);
  public List<Movie> findByChanelId(long chanelId);
}
