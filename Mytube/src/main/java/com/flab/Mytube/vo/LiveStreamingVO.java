package com.flab.Mytube.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data // getter, setter 한번에 받기
public class LiveStreamingVO {
    private long id;
    private String title;
    private String contents;
    private int userCount;
    private int thumbsUp;
    private DateTimeFormat reservedAt;
    private DateTimeFormat updatedAt;
    private DateTimeFormat deletedAt;
}
