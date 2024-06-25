package com.flab.Mytube.mapper;

import com.flab.Mytube.dto.movie.request.ReserveShowRequest;
import com.flab.Mytube.dto.movie.request.ThumbsUpRequest;
import com.flab.Mytube.dto.streaming.LiveStreamingDTO;
import com.flab.Mytube.vo.LiveStreamingVO;
import com.flab.Mytube.vo.MovieVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface LiveMapper {

  long getPreferCount(@Param("request") ThumbsUpRequest request);

  void reserveShow(@Param("request") LiveStreamingDTO request);

  LiveStreamingVO findByStartingLiveId(@Param("id") long id);

  MovieVO getMovieUri(@Param("id") long id);
}
