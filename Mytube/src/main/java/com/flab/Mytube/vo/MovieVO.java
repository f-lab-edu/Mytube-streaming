package com.flab.Mytube.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MovieVO {
    private long id;
    private long userId;
    private String subject;
    private String url;
    private Date createdAt;
    private Date deletedAt;
}
