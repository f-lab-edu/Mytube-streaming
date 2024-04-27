package com.flab.Mytube.dto.movie.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
public class InsertPostResponse {
    private BigInteger ID;
    private int code;
    private String message;
}
