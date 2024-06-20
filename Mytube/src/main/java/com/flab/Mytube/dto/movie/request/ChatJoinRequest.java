package com.flab.Mytube.dto.movie.request;

import lombok.Builder;
import lombok.Getter;


@Getter
public class ChatJoinRequest {
    private int userId;
    private String chatContents;
    @Builder
    public ChatJoinRequest(int userId, String chatContents){
        this.userId = userId;
        this.chatContents = chatContents;
    }
}
