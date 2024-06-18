package com.flab.Mytube.service;

import com.flab.Mytube.dto.streaming.LiveStatus;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

@Service
public class LiveStatusService {
    @Resource(name = "statusTemplate")
    private HashOperations<String, String, LiveStatus> hashOperations;

    private static String START="restart"; // 상수 모으는 파일 만들기
    private static String END="end"; // 상수 모으는 파일 만들기
    private static String STOP="stop"; // 상수 모으는 파일 만들기

    public void writeHash(String key, long id) {
        LiveStatus status = new LiveStatus(id);
//        System.out.println(stored.toString());

        hashOperations.put(key, String.valueOf(id), status);
        LiveStatus stored = hashOperations.get(key,String.valueOf(id));
        System.out.println(stored.toString()); // 저장한 거 확인
    }

    public boolean isContain(String key, long id){
        if(hashOperations.hasKey(key, String.valueOf(id))){
            return true;
        }
        return false;
    }

    // 라이브 시작
    public void startLive(long liveId){
        String key = String.join("LIVE", String.valueOf(liveId));
        if(isContain(key, liveId)){
            System.err.println(" [ ERROR 0618T0641 ] 잘못된 요청입니다. ");
        }
        writeHash(key, liveId);
    }

    // 라이브 일시 정지
    public void liveStop(long liveId){
        String key = String.join("LIVE", String.valueOf(liveId));
        if(isContain(key, liveId)){
            LiveStatus live = hashOperations.get(key, String.valueOf(liveId));
            live.changeState(STOP);
        }
        System.err.println(" [ ERROR 0618T0648 ] 잘못된 요청입니다. ");
    }

    // 라이브 중간 참여 요청 : 레디스에서 데이터 불러오기

    // 라이브 종료: 레디스 삭제

//    public void liveOn(long id){
//        String key = String.join("LIVE", String.valueOf(id));
//        if(!map.containsKey(key)){
//            map.put(key, new LiveStatus(id));
//            return;
//        }
//        LiveStatus live = map.get(key);
//        live.changeState(START);
//    }
//
//    public void liveEnd(long id){
//        String key = String.join("LIVE", String.valueOf(id));
//        if(!map.containsKey(key)){
//            map.put(key, new LiveStatus(id));
//            return;
//        }
//        LiveStatus live = map.get(key);
//        live.changeState(END);
//        map.remove(key);
//    }
}
