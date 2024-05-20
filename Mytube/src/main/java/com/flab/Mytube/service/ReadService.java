package com.flab.Mytube.service;

import com.flab.Mytube.dao.LiveListDAO;
import com.flab.Mytube.dao.LivePageDAO;
import com.flab.Mytube.dao.LiveStreamingDAO;
import com.flab.Mytube.mapper.ReadMapper;
import com.flab.Mytube.vo.LiveStreamingVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadService {
    private final ReadMapper readMapper;

    @Inject
    private LiveStreamingDAO liveListDAO ;


    public void delete(long id){
        readMapper.liveDelete(id);
    }

    //현재 채널 지난 라이브 다시보기
    //    조회하는 코드에서 deletedAt에 값이 있다면 불러오지 못하게 처리할 것
    public void replay(long streamerId, long movieId){
//        //mapper 연결
//        ----  id= movieReplay
//        SELECT url // 조회
//        from liveStreaming
//        where id = id ;
    }

    //    현재 채널 라이브 및 동영상 목록 조회
//    조회하는 코드에서 deletedAt에 값이 있다면 불러오지 못하게 처리할 것
//    https://authorkim0921.tistory.com/7 (참고)
    public List<LiveStreamingVO> getLiveList(long streamerId) throws Exception{
//        ----  id= getLiveList
//        SELECT id, title from liveStreaming where userId = streamerId and deletedAt is NULL;
        return liveListDAO.list();
    }
}
