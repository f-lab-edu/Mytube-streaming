package com.flab.Mytube.mapper_v1;

import com.flab.Mytube.dto.movie.request.ThumbsUpRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMappers {

  long checkPrefer(@Param("request") ThumbsUpRequest request);

  long updatePrefer(@Param("request") ThumbsUpRequest request);

  long deletePrefer(@Param("request") ThumbsUpRequest request);

  long addPrefer(@Param("request") ThumbsUpRequest request);

}
