package com.flab.Mytube.service;

import org.springframework.stereotype.Service;

@Service
public class ReadService {
        //라이브 삭제
        //    관련 라이브 테이블에서 deletedAt 날짜 추가하기
        //    조회하는 코드에서 이 부분에 값이 있다면 불러오지 못하게 처리할 것
    public void delete(long streamerId, long movieId){ //dto 로 만들기
//        ----- id= liveDelete
//        ALTER INTO // 수정
//        live_streaming (deletedAt) // 시간
//        VALUES
//        { timeStamp 같은 거 알아보기 }
//        where == streamerId, == movieId
    }

    //현재 채널 지난 라이브 다시보기
    //    조회하는 코드에서 deletedAt에 값이 있다면 불러오지 못하게 처리할 것
    public void replay(long streamerId, long movieId){
//        //mapper 연결
//        ----  id= movieReplay
//        SELECT url? // 조회
//        from liveStreaming
//        where == streamerId, == movieId
    }

    //    현재 채널 라이브 및 동영상 목록 조회
//    조회하는 코드에서 deletedAt에 값이 있다면 불러오지 못하게 처리할 것
    public void getLiveList(long streamerId){
//        ----  id= getLiveList
//        SELECT url? // 조회
//        from liveStreaming
//        where == streamerId
    }
}
