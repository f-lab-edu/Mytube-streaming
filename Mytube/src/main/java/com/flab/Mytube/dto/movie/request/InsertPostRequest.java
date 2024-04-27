package com.flab.Mytube.dto.movie.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import oracle.sql.TIMESTAMP;

import java.math.BigInteger;

public class InsertPostRequest {
    private InsertPostRequest(){throw new IllegalStateException();}

    @Getter
    @AllArgsConstructor
    public static class Param{
        BigInteger streamer_id;
        int movie_id;
        String title;
        String content;
        TIMESTAMP reserved_time;
    }
}
