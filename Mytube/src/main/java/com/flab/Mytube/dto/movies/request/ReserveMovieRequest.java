package com.flab.Mytube.dto.movies.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import oracle.sql.TIMESTAMP;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReserveMovieRequest {
    private int streamerId;
    private int movieId;
    private String title;
    private String content;
}
