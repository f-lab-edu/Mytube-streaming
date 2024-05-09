package com.flab.Mytube.service;

import com.flab.Mytube.dto.movie.request.ChatJoinRequest;
import com.flab.Mytube.mapper.LiveMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LiveService {
    private final LiveMapper liveMapper;
    public void requestJoin(ChatJoinRequest request){

    }
    public void endLive(long id){

    }

    public void prefer(long liveId, long userId){
        if(liveMapper.checkPrefer(liveId, userId) > 0){
            // 기존에 좋아요를 누른 경우
            //    1. 해당 좋아요 테이블 삭제
            //    2. thumbs_up 값 1 줄여서 update
            liveMapper.deletePrefer(liveId, userId);
            return;
        }
        liveMapper.updatePrefer(liveId, userId);
    }
}
