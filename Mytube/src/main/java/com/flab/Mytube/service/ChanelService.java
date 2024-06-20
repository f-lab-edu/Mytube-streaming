package com.flab.Mytube.service;

import com.flab.Mytube.dao.LivePageDAO;
import com.flab.Mytube.dao.LiveStreamingDAO;
import com.flab.Mytube.mapper.ReadMapper;
import com.flab.Mytube.vo.LiveStreamingVO;
import com.flab.Mytube.vo.MovieVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChanelService {
    @Autowired
    private final ReadMapper readMapper;

//    @Inject
//    private LiveStreamingDAO liveListDAO ;


    public void delete(long liveId){
        LiveStreamingVO live=readMapper.getLiveContents(liveId);
        /* 이미 삭제되지는 않았는지 확인 */
        readMapper.liveDelete(liveId);
    }


    // 지난 라이브 조회
    public void replay(long liveId){
        LiveStreamingVO live=readMapper.getLiveContents(liveId);
        if(live == null){
            System.err.println("찾으려는 데이터가 존재하지 않습니다. ");
            return;
        }
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

    // 업로드한 동영상 목록 조회
    // 업로드한 동영상 리스트 뽑아오는 코드 필요할 듯? id 랑 subject 반환해주기
    @Transactional
    public List<MovieVO> getUploadMovie(long chanelId){
        // sreamerId 와 연관된 동영상 반환해오기
        List<MovieVO> result = readMapper.uploadMovieList(chanelId);
        return result;
    }
}
