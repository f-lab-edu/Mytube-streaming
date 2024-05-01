package com.flab.Mytube.dto.user;

import com.flab.Mytube.dto.TimeDTO;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends TimeDTO {
    private long id;
    @NonNull
    private String password;
    @NonNull
    private String nickname;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updatedAt;


    public void setCreatedAtToNow() {
        updatedAt = LocalDateTime.now();
    }
}
