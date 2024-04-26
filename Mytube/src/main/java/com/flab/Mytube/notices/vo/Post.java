package com.flab.Mytube.notices.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Post {
    private Long id;
    private String userName;
    private String title;
    private String postContent;

    private Date regDate;
    private Date updtDate;

    public Post(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.postContent = content;
    }

    public Post(String userName, String title, String content) {
        this.userName = userName;
        this.title = title;
        this.postContent = content;
        this.regDate = new Date();
        this.updtDate = new Date();
    }

    public Post(Long id, String userName, String title, String content) {
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.postContent = content;
        this.regDate = new Date();
        this.updtDate = new Date();
    }

    public Post(Long id, String userName, String title, String content, Date regDate, Date updtDate) {
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.postContent = content;
        this.regDate = regDate;
        this.updtDate = updtDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("uid : ").append(id).append("\n");
        sb.append("user : ").append(id).append("\n");
        sb.append("title : ").append(title).append("\n");
        sb.append("postContent : ").append(postContent).append("\n");
        return sb.toString();
    }
}
