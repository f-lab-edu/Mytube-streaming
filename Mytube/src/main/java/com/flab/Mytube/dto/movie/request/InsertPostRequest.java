package com.flab.Mytube.dto.movie.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class InsertPostRequest {
    private InsertPostRequest(){throw new IllegalStateException();}

    @Getter
    @AllArgsConstructor
    public static class Param{
        BigInteger streamer_id;
        int movie_id;
        String title;
        String content;
        LocalDateTime reserved_time;
    }
}
