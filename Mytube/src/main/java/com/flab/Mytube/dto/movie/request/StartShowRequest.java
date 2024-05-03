package com.flab.Mytube.dto.movie.request;

import lombok.*;

@Getter
@NoArgsConstructor
public class StartShowRequest {
    private long streamingId;

    @Builder
    public StartShowRequest(long streamingId){
        this.streamingId = streamingId;
    }

    public StartShowRequest startShowRequest(){
        return StartShowRequest.builder()
                .streamingId(streamingId).build();
    }
}
