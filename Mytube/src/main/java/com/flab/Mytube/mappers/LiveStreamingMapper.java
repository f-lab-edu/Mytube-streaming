package com.flab.Mytube.mappers;

import com.flab.Mytube.domain.LiveStreaming;
import jakarta.annotation.Resource;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Resource
public interface LiveStreamingMapper {
  public void save (@Param("request") LiveStreaming request);
  public void delete(@Param("steramingId") long steramingId);
  public LiveStreaming findByLiveId(@Param("liveId") long liveId);
  public List<LiveStreaming> findByChannelId(@Param("channelId") Long channelId);
}
