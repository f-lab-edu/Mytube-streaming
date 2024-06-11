package com.flab.Mytube.dto.movie.request;

import lombok.*;

@Getter
@NoArgsConstructor
public class StartShowRequest {
    private long liveId;

    @Builder
    public StartShowRequest(long liveId){
        this.liveId = liveId;
    }

    public StartShowRequest startShowRequest(){
        return StartShowRequest.builder()
                .liveId(liveId).build();
    }
}
