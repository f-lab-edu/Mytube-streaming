package com.flab.Mytube.dto.movie;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LiveStreamingDTO {
    @NotNull
    private long id;
    @NotNull
    private long userId;
    @NotNull
    private long movieId;
    @NotNull
    private String title;

    private String contents;
    private int userCount = 0;
    private int thumbsUp = 0;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime reservedTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updatedAt;


    public void setCreatedAtToNow() {
        createdAt = LocalDateTime.now();
    }

    public void setUpdatedAtToNow() {
        updatedAt = LocalDateTime.now();
    }
}
