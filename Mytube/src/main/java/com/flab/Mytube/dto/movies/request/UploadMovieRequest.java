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
public class UploadMovieRequest {
    private int streamerId;
    private String subject;
    private String url;
//    private TIMESTAMP created;
}
