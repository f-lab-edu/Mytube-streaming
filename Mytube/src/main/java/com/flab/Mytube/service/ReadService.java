package com.flab.Mytube.service;

import com.flab.Mytube.dao.LivePageDAO;
import com.flab.Mytube.dao.LiveStreamingDAO;
import com.flab.Mytube.mapper.ReadMapper;
import com.flab.Mytube.vo.LiveStreamingVO;
import com.flab.Mytube.vo.MovieVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadService {
    @Autowired
    private final ReadMapper readMapper;

    @Inject
    private LiveStreamingDAO liveListDAO ;


    public void delete(long liveId){
        LiveStreamingVO live=readMapper.getLiveContents(liveId);
        /* 이미 삭제되지는 않았는지 확인 */
        readMapper.liveDelete(liveId);
    }


    public void replay(long liveId){
        LiveStreamingVO live=readMapper.getLiveContents(liveId);
        long movieId = live.getMovieId();
        MovieVO movie = readMapper.getMovieUrl(movieId);
        /* checklist
        * : 데이터를 받아오지 않을 경우 예외처리(deletedAt : not null)
        * : responseDTO 설계: title, contents, url, userCount, thumbsUp, reservedAt, updatedAt
        * */

    }

    //    현재 채널 라이브 및 동영상 목록 조회
//    조회하는 코드에서 deletedAt에 값이 있다면 불러오지 못하게 처리할 것
//    https://authorkim0921.tistory.com/7 (참고)
    public List<LivePageDAO> getLiveList(long userId) {
//        List<LiveStreamingVO> lists =  liveListDAO.list();
        return readMapper.getLiveList(userId);
    }
}
