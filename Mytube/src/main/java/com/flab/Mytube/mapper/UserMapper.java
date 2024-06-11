package com.flab.Mytube.mapper;

import com.flab.Mytube.dto.movie.request.ThumbsUpRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    long checkPrefer(@Param("request") ThumbsUpRequest request);
    long updatePrefer(@Param("request") ThumbsUpRequest request);
    long deletePrefer(@Param("request") ThumbsUpRequest request);
    long addPrefer(@Param("request") ThumbsUpRequest request);

}