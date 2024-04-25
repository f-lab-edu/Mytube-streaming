package com.flab.Mytube.dto.movies.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class ReserveMovieResponse {
    private String liveId;
}
