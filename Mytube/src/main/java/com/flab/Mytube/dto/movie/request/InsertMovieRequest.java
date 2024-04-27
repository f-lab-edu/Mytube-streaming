package com.flab.Mytube.dto.movie.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

public class InsertMovieRequest {
    private InsertMovieRequest(){
        throw new IllegalStateException();
    }

    @Getter
    @Setter
    public static class Param{
        BigInteger streamer_id;
        String subject;
        String url;
    }
}
