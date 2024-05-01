package com.flab.Mytube.movie.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class InsertMovieRequest {
    long ID;
    BigInteger streamer_id;
    String subject;
    String url;
}
