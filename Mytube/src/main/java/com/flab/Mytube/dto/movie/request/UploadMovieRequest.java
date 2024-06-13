package com.flab.Mytube.dto.movie.request;

import com.flab.Mytube.dto.movie.MovieDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UploadMovieRequest {
    long id;
    long chanelId;
    String subject;
    String url;
    @Builder
    public UploadMovieRequest(long id, long chanelId, String subject, String url){
        this.id = id;
        this.chanelId = chanelId;
        this.subject = subject;
        this.url = url;
    }

    public MovieDTO uploadMovie(){
        return MovieDTO.builder()
                .id(id)
                .chanelId(chanelId)
                .subject(subject)
                .url(url)
                .build();
    }
}
