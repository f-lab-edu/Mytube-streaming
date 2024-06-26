package com.flab.Mytube.dto.movie.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InsertMovieResponse {
    private long id;
    private int code;
    private String message;
}
