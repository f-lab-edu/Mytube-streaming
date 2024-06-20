package com.flab.Mytube.dto.movie.request;

import lombok.*;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ThumbsUpRequest {
    private long liveId;
    private long userId;
    private long count;

    public ThumbsUpRequest(long liveId){
        this.liveId=liveId;
    }

    public void minusCount(){
        this.count -= 1;
    }
    public void plusCount(){
        this.count += 1;
    }
}
