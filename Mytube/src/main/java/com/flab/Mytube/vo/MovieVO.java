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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("id: "+this.id+"\n");
        sb.append("userId: "+this.userId+"\n");
        sb.append("subject: "+this.subject+"\n");
        sb.append("url: "+this.url+"\n");
        sb.append("createdAt: "+this.createdAt+"\n");
        sb.append("deletedAt: "+this.deletedAt);
        return sb.toString();
    }
}
