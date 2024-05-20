package com.flab.Mytube.dao;

import org.springframework.format.annotation.DateTimeFormat;

public class LivePageDAO {
    private long id;
    private String title;
    private String contents;
    private int userCount;
    private int thumbsUp;
    private DateTimeFormat reservedAt;
    private DateTimeFormat updatedAt;
    private DateTimeFormat deletedAt;
}
