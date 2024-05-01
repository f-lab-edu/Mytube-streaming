package com.flab.Mytube.mapper;

import com.flab.Mytube.movie.request.InsertPostRequest;
import com.flab.Mytube.movie.request.JoinChatRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
@Mapper
public interface PostMapper {
    BigInteger addPost(@Param("param")InsertPostRequest.Param param);
    BigInteger joinChat(JoinChatRequest param);
}
