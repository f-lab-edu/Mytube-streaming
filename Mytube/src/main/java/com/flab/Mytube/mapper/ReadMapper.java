package com.flab.Mytube.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ReadMapper {
    void liveDelete(@Param("request"));
    void movieReplay(@Param("request"));
    void getLiveList(@Param("request"));
}
