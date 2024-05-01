package com.flab.Mytube.dto.movie.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class InsertMovieRequest {
    long id;
    BigInteger streamer_id;
    String subject;
    String url;
}
