package com.flab.Mytube.mapper;

import com.flab.Mytube.dao.LiveListDAO;
import com.flab.Mytube.dao.LivePageDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ReadMapper {
    void liveDelete(@Param("request") long movieId);
    LivePageDAO movieReplay(@Param("request") long movieId);
    List<LiveListDAO> getLiveList(@Param("request") long streamerId);
}
