package com.flab.Mytube.mappers;

import com.flab.Mytube.domain.LiveStreaming;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LiveStreamingMapper {
  public void save (LiveStreaming request);
  public void delete(long chanelId);
  public LiveStreaming getLiveId(long liveId);
  public LiveStreaming findByLiveId(long liveId);
  public List<LiveStreaming> findByChanelId(Long chanelId);
}
