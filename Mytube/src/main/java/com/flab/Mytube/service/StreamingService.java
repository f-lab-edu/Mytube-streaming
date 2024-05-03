package com.flab.Mytube.service;

import com.flab.Mytube.dto.movie.LiveStreamingDTO;
import com.flab.Mytube.dto.movie.request.ReserveShowRequest;
import com.flab.Mytube.dto.movie.request.JoinChatRequest;
import com.flab.Mytube.dto.movie.response.ReserveShowResponse;
import com.flab.Mytube.dto.movie.response.JoinChatResponse;
import com.flab.Mytube.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class StreamingService {
    private final PostMapper postMapper;

    @Transactional
    public ReserveShowResponse reserveMovie(ReserveShowRequest request){
        LiveStreamingDTO liveStreaming = LiveStreamingDTO.builder()
                .movieId(request.getMovieId())
                .streamerId(request.getStreamerId())
                .title(request.getTitle())
                .contents(request.getContents())
                .reservedTime(request.getReserved_time())
                .build();
        postMapper.reserveShow(liveStreaming);
//        long resultId =liveStreaming.getId();
//        liveStreaming.setId(12);
//        long resultId =liveStreaming.getId();

//        System.out.println(" >>> "+liveStreaming.getUserCount());
        return new ReserveShowResponse( 201, "Success");
    }

    @Transactional
    public JoinChatResponse joinChat(JoinChatRequest param, BigInteger movie_id){
        param.setMovie_id(movie_id);
        BigInteger contentsID= postMapper.joinChat(param);
        return new JoinChatResponse(contentsID, 201, "Success");
    }
}
