package com.flab.Mytube.mapper_v1;

import com.flab.Mytube.dao.LivePageDAO;
import com.flab.Mytube.vo.LiveStreamingVO;
import com.flab.Mytube.vo.MovieVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ReadMapper {

  void liveDelete(@Param("movieId") long movieId);

  LiveStreamingVO getLiveContents(@Param("liveId") long liveId);

  MovieVO getMovieUrl(@Param("movieId") long movieId);

  List<LivePageDAO> getLiveList(@Param("userId") long userId);

  List<MovieVO> uploadMovieList(@Param("userId") long userId);
}
