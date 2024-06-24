package com.flab.Mytube.vo;

import lombok.Data;

import java.util.Date;
import lombok.ToString;

@ToString
@Data // getter, setter 한번에 받기
public class LiveStreamingVO {

  private long id;
  private long movieId;
  private String title;
  private String contents;
  private int userCount;
  private int thumbsUp;
  private Date reservedAt;
  private Date updatedAt;
  private Date deletedAt;

  public boolean isEmpty(){
    if(title == null || title.equals(""))
      return true;
    return false;
  }
}
