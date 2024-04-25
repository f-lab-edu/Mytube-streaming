package com.flab.Mytube.dto.movies.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartMovieRequest {
    private int streamerId;
    private int movieId;
}
