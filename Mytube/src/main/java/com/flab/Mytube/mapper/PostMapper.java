package com.flab.Mytube.mapper;

import com.flab.Mytube.dto.streaming.LiveStreamingDTO;
import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import com.flab.Mytube.vo.LiveStreamingVO;
import com.flab.Mytube.vo.MovieVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PostMapper {

  long addMovie(@Param("request") FileUploadRequest movie);

  long reserveShow(@Param("request") LiveStreamingDTO liveStreaming);

  MovieVO getMovieUrl(@Param("movieId") long movieId);

  LiveStreamingVO findByStartingLiveId(@Param("id") long id);

  List<MovieVO> uploadMovieList(@Param("userId") long userId);

  //    -----
  long selectShow(@Param("request") LiveStreamingDTO liveStreaming);
}
