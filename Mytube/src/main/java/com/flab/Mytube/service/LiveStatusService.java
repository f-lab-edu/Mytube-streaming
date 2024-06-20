package com.flab.Mytube.service;

import com.flab.Mytube.Utils.Movies;
import com.flab.Mytube.dto.movie.request.WatchLiveRequest;
import com.flab.Mytube.dto.streaming.LiveStatus;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Service
public class LiveStatusService {
    @Resource(name = "statusTemplate")
    private HashOperations<String, String, LiveStatus> hashOperations;
    static Movies movie = new Movies();

    public boolean isContain(String key, long id) {
        if (hashOperations.hasKey(key, String.valueOf(id))) {
            return true;
        }
        return false;
    }

    // 라이브 시작
    public void startLive(long liveId, String url) {
        String key = String.join("LIVE", String.valueOf(liveId));
//        if (isContain(key, liveId)) {
//            System.err.println(" [ ERROR 0618T0641 ] 이미 시작한 라이브 입니다. ");
//            return;
//        }
        // 새로운 객체 캐시에 작성
        LiveStatus status = new LiveStatus(liveId, url);
        hashOperations.put(key, String.valueOf(liveId), status);
    }

    // 라이브 재시작
    public void restartLive(long liveId){
        String key = String.join("LIVE", String.valueOf(liveId));
        if (isContain(key, liveId)) {
            LiveStatus live = hashOperations.get(key, String.valueOf(liveId));
            // 라이브 상태 정지시키고 캐시 저장
            live.startLive();
            hashOperations.put(key, String.valueOf(liveId), live);
            return;
        }
        System.err.println(" [ ERROR 0619T1439 ] 잘못된 요청입니다. ");
    }

    // 라이브 일시 정지
    public void stopLive(long liveId) {
        String key = String.join("LIVE", String.valueOf(liveId));
        if (isContain(key, liveId)) {
            LiveStatus live = hashOperations.get(key, String.valueOf(liveId));
            // 라이브 상태 정지시키고 캐시 저장
            live.stopLive();
            hashOperations.put(key, String.valueOf(liveId), live);
            return;
        }
        System.err.println(" [ ERROR 0618T0648 ] 잘못된 요청입니다. ");
    }

    // 라이브 종료
    public void endLive(long liveId) {
        String key = String.join("LIVE", String.valueOf(liveId));
        if (isContain(key, liveId)) {
            LiveStatus live = hashOperations.get(key, String.valueOf(liveId));
            live.endLive();

            System.out.println("service code >>> "+live.getStatus());
            // TODO : 관련 캐시 삭제하기
            hashOperations.put(key, String.valueOf(liveId), live);
            return;
        }
        System.err.println(" [ ERROR 0619T1436 ] 잘못된 요청입니다. ");
    }

    // TODO: 라이브 중간 참여 요청 : 레디스에서 데이터 불러오기
    public File joinLive(WatchLiveRequest request) {
        int id = request.getLiveId();
        String key = String.join("LIVE", String.valueOf(id));
        //        if (!hashOperations.hasKey(key, String.valueOf(liveId))) {
//            System.err.println(" [ ERROR 0618T0719 ] 해당 라이브는 존재하지 않습니다. ");
//            return null;
//        }
        LiveStatus stored = hashOperations.get(key, String.valueOf(id));
//        liveId 로 id 가 건너올 경우 -> return m3u8;
        if(isNumberic(request.getChanelId()) == true){
            // stored 에서 이어보게 될 구간 확인
            return movie.createM3U8(stored.getM3u8Url(), stored.getTsIndex());
        }
        return new File(stored.getBasePath(request.getChanelId()));
//        return new File(request.getLiveId());
////        liveId 로 ts 파일 이름이 건너올 경우:
//
//        if(isNumberic(liveId) != true){ // ts 기반 반환
////            LiveStatus live = hashOperations.get(key, String.valueOf(liveId));
////            String path = live.getM3u8Url();
////            path.replace(".m3u8", )
////            return new File(path);
//        }
//        // stored 에서 이어보게 될 구간 확인
//        return movie.createM3U8(stored.getM3u8Url(), stored.getTsIndex());
    }

    public boolean isNumberic(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        } catch(Exception e){
            System.err.println("[ Error 0620T1010 ] 숫자 검증 실패");
            e.printStackTrace();
        }
        return true;
    }


    // 라이브 상태 업데이트
    public void currentLive(long liveId, LocalTime time) {
        String key = String.join("LIVE", String.valueOf(liveId));
        if (isContain(key, liveId) == false) {
            System.err.println(" [ ERROR 0618T0719 ] 해당 라이브는 존재하지 않습니다. ");
            return;
        }
        LiveStatus stored = hashOperations.get(key, String.valueOf(liveId));
        stored.updateCurrent(time);
        hashOperations.put(key, key, stored);
    }
}
