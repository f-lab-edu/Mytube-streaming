package com.flab.Mytube.dto.movie.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
public class InsertPostResponse {
    private BigInteger id;
    private int code;
    private String message;
}
