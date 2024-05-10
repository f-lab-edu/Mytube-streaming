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

    }
    public void endLive(long id){

    }

    // 좋아요 서비스
    public void prefer(ThumbsUpRequest request){
        long count = liveMapper.getPreferCount(request);
        if(liveMapper.checkPrefer(request) > 0){ // 좋아요 테이블이 1개 이상 존재
            count -=1;
            request.setCount(count);
            liveMapper.updatePrefer(request);
            liveMapper.deletePrefer(request);
            return;
        }
        //    2. 좋아요를 누르지 않은 경우 :탐색 -> thumbs_up 값 1 올려서 update
        count +=1;
        request.setCount(count);
        liveMapper.addPrefer(request);
        liveMapper.updatePrefer(request);
    }
}
