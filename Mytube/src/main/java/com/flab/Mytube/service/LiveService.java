package com.flab.Mytube.service;

import com.flab.Mytube.dto.movie.request.ChatJoinRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class LiveService {
    public void requestJoin(ChatJoinRequest request){

    }
    public void endLive(long id){

    }

    public void prefer(long liveId, long userId){
        // liveId 에 맞는 게시물 불러오기
        // 중복 좋아요를 막으려면 해당 유저가 좋아한 게시글도 알아야 할 것 같은데?
        // 좋아요 관련 테이블 새로 만들까.
    }
}
