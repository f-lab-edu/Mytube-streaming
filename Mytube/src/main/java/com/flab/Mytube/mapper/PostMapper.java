package com.flab.Mytube.mapper;

import com.flab.Mytube.dto.movie.LiveStreamingDTO;
import com.flab.Mytube.dto.movie.MovieDTO;
import com.flab.Mytube.dto.movie.request.FileUploadRequest;
import com.flab.Mytube.dto.movie.response.StartingShowResponse;
import com.flab.Mytube.vo.LiveStreamingVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface PostMapper {
    long addMovie(@Param("request") FileUploadRequest movie);
    long reserveShow(@Param("request") LiveStreamingDTO liveStreaming);
    LiveStreamingVO findByStartingStreamingId(@Param("id") long id);
    //    -----
    long selectShow(@Param("request") LiveStreamingDTO liveStreaming);
//    BigInteger joinChat(JoinChatRequest param);
}
