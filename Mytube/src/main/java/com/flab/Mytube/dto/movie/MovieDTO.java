package com.flab.Mytube.dto.movie;

import com.flab.Mytube.dto.TimeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO extends TimeDTO {
    private long id;
    private long userId;
    private String subject;
    private String url;
}
