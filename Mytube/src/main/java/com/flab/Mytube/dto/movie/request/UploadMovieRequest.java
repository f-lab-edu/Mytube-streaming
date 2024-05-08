package com.flab.Mytube.dto.movie.request;

import com.flab.Mytube.dto.movie.MovieDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadMovieRequest {
    long id;
    long streamerId;
    String subject;
    String url;
    @Builder
    public UploadMovieRequest(long id, long streamerId, String subject, String url){
        this.id = id;
        this.streamerId = streamerId;
        this.subject = subject;
        this.url = url;
    }

    public MovieDTO uploadMovie(){
        return MovieDTO.builder()
                .id(id)
                .streamerId(streamerId)
                .subject(subject)
                .url(url)
                .build();
    }
}
