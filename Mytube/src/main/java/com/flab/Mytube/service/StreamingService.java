package com.flab.Mytube.service;

import com.flab.Mytube.dto.movie.request.InsertPostRequest;
import com.flab.Mytube.dto.movie.request.JoinChatRequest;
import com.flab.Mytube.dto.movie.response.InsertPostResponse;
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
    public InsertPostResponse reserveMovie(InsertPostRequest.Param param){
        BigInteger resultID = postMapper.addPost(param);
        return new InsertPostResponse(resultID, 201, "Success");
    }

    @Transactional
    public JoinChatResponse joinChat(JoinChatRequest param, BigInteger movie_id){
        param.setMovie_id(movie_id);
        BigInteger contentsID= postMapper.joinChat(param);
        return new JoinChatResponse(contentsID, 201, "Success");
    }
}
