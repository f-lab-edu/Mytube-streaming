package com.flab.Mytube.service;

import com.flab.Mytube.dto.movie.request.ChatJoinRequest;
import com.flab.Mytube.dto.movie.request.ThumbsUpRequest;
import com.flab.Mytube.mapper.LiveMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LiveService {
    private final LiveMapper liveMapper;
    public void requestJoin(ChatJoinRequest request){
        System.out.println("채팅 참여하기 얍!");
        System.out.println(request.toString());
    }
    public void endLive(long id){
        // 데이터가 있는지 먼저 확인?
        System.out.println(id+" 번 라이브가 종료되었습니다.");
    }

    // 좋아요 서비스
    public void prefer(ThumbsUpRequest request){
        if(liveMapper.checkPrefer(request) > 0){ // 좋아요 테이블이 1개 이상 존재
            request.minusCount();
            liveMapper.updatePrefer(request);
            liveMapper.deletePrefer(request);
            return;
        }
        //    2. 좋아요를 누르지 않은 경우 :탐색 -> thumbs_up 값 1 올려서 update
        request.plusCount();
        liveMapper.addPrefer(request);
        liveMapper.updatePrefer(request);
    }
}
