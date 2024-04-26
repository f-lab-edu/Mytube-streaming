package com.flab.Mytube.dto.movies.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReserveMovieResponse {
    private int code;
    private String liveId;
    private String message;
}
