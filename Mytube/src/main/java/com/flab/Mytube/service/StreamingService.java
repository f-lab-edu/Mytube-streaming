package com.flab.Mytube.service;

import com.flab.Mytube.dto.movie.request.UploadMovieRequest;
import com.flab.Mytube.dto.movie.request.ReserveShowRequest;
import com.flab.Mytube.dto.movie.response.Response;
import com.flab.Mytube.dto.movie.response.StartingShowResponse;
import com.flab.Mytube.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StreamingService {
    private final PostMapper postMapper;

    @Transactional // 동영상 업로드
    public Response insertMovie(UploadMovieRequest request){
        postMapper.addMovie(request.uploadMovie());
        return new Response( 201, "Success");
    }

    @Transactional // 방송 예약하기
    public Response reserveMovie(ReserveShowRequest request){
        postMapper.reserveShow(request.makeReservation());
        return new Response( 201, "Success");
    }

    @Transactional // 방송 시작하기
    public StartingShowResponse startShow(long streamingId){
// Id 를 활용해 테이블을 찾아오는 매퍼 호출
//        return mapper.method(streamingId);
        StartingShowResponse response = postMapper.findByStartingStreamingId(streamingId);
        return response;
    }


    // ----------

//    @Transactional // 채팅 참여하기
//    public JoinChatResponse joinChat(JoinChatRequest param, BigInteger movie_id){
//        param.setMovie_id(movie_id);
//        BigInteger contentsID= postMapper.joinChat(param);
//        return new JoinChatResponse(contentsID, 201, "Success");
//    }
}
