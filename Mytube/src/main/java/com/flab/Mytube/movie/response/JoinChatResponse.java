package com.flab.Mytube.movie.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
public class JoinChatResponse {
    private BigInteger ID;
    private int code;
    private String message;
}
