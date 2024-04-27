package com.flab.Mytube.dto.movie.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigInteger;

@Getter
@AllArgsConstructor
public class InsertMovieResponse {
    private BigInteger ID;
    private int code;
    private String message;
}
