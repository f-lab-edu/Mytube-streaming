package com.flab.Mytube.dto.movies.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadMovieResponse {
    private int code;
    private BigInteger movieId;
    private String message;
}
