package com.flab.Mytube.dto.movie.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadMovieRequest {
    long id;
    long streamerId;
    String subject;
    String url;
}
