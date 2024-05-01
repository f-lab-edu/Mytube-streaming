package com.flab.Mytube.movie.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;
@Getter
@AllArgsConstructor
public class JoinChatRequest {
    BigInteger userId;
    String chatContents;
    BigInteger movie_id;
    public void setMovie_id(BigInteger id) {
        this.movie_id=id;
    }
}
