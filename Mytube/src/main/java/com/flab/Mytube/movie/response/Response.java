package com.flab.Mytube.movie.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Response {
    private int code;
    private String message;
}
