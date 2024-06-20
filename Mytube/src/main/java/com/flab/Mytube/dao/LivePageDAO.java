package com.flab.Mytube.dao;

import lombok.Data;
import lombok.Getter;
import java.util.Date;

@Data
@Getter
public class LivePageDAO {
    private long id;
    private long userId; // should match the column name in SQL query
    private String title;
    private String contents;
    private int userCount;
    private int thumbsUp;
    private Date reservedAt;
    private Date updatedAt;
    private Date deletedAt;
}
