package com.flab.Mytube.mapper;

import com.flab.Mytube.dto.movie.LiveStreamingDTO;
import com.flab.Mytube.dto.movie.request.JoinChatRequest;
import com.flab.Mytube.dto.movie.response.StartingShowResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
@Mapper
public interface PostMapper {
    long reserveShow(@Param("request") LiveStreamingDTO liveStreaming);
    long selectShow(@Param("request") LiveStreamingDTO liveStreaming);
    BigInteger joinChat(JoinChatRequest param);
    StartingShowResponse findByStartingStreamingId(@Param("id") long id);
}
