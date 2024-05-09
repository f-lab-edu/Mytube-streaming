package com.flab.Mytube.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface LiveMapper {
    long checkPrefer(@Param("request") long liveId, long userId);
    long updatePrefer(@Param("request") long liveId, long userId);
    long deletePrefer(@Param("request") long liveId, long userId);
}
