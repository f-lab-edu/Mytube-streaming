package com.flab.Mytube.dto.movie.request;

import com.flab.Mytube.dto.movie.LiveStreamingDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReserveShowRequest {
    private long streamerId;
    private long movieId;
    private String title;
    private String contents;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime reservedTime;

    @Builder
    public ReserveShowRequest(long streamerId, long movieId, String title, String contents, LocalDateTime dateTime){
        this.streamerId = streamerId;
        this.movieId=movieId;
        this.title=title;
        this.contents=contents;
        this.reservedTime =dateTime;
    }

    public LiveStreamingDTO makeReservation(){
        return LiveStreamingDTO.builder()
                .streamerId(streamerId)
                .movieId(movieId)
                .title(title)
                .contents(contents)
                .reservedTime(reservedTime)
                .build();
    }
}
