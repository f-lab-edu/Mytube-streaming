package com.flab.Mytube.dto.movie.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ThumbsUpRequest {
    private long liveId;
    private long userId;
    private long count;
}
