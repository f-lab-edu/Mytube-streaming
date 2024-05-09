package com.flab.Mytube.dto.movie.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

//	"user_id" : int ,
//	"chat_contents" :String
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
