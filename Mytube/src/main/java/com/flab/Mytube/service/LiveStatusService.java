package com.flab.Mytube.service;

import com.flab.Mytube.Utils.TimeManager;
import com.flab.Mytube.dto.streaming.LiveStatus;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@Service
public class LiveStatusService {
    @Resource(name = "statusTemplate")
    private HashOperations<String, String, LiveStatus> hashOperations;

    private static String START = "restart"; // 상수 모으는 파일 만들기
    private static String END = "end"; // 상수 모으는 파일 만들기
    private static String STOP = "stop"; // 상수 모으는 파일 만들기
    static ExecutorService executor = Executors.newCachedThreadPool();

    public void writeHash(String key, long id) {
        LiveStatus status = new LiveStatus(id);
        hashOperations.put(key, String.valueOf(id), status);
//        LiveStatus stored = hashOperations.get(key, String.valueOf(id));
    }

    public boolean isContain(String key, long id) {
        if (hashOperations.hasKey(key, String.valueOf(id))) {
            return true;
        }
        return false;
    }

    // 라이브 시작
    public void startLive(long liveId) {
        String key = String.join("LIVE", String.valueOf(liveId));
//        if (isContain(key, liveId)) {
//            System.err.println(" [ ERROR 0618T0641 ] 이미 시작한 라이브 입니다. ");
//            return;
//        }
        CompletableFuture<Integer> future = new CompletableFuture<>();
        writeHash(key, liveId);
        LiveStatus live = hashOperations.get(key, String.valueOf(liveId));
        TimeManager timer = new TimeManager(live);
        // 라이브 진행 상황 저장
        executor.submit(()->future.complete(timer.calling()));
    }

    // 라이브 일시 정지
    public void liveStop(long liveId) {
        String key = String.join("LIVE", String.valueOf(liveId));
        if (isContain(key, liveId)) {
            LiveStatus live = hashOperations.get(key, String.valueOf(liveId));
            live.endLive();

            System.out.println("service code >>> "+live.getStatus());

            hashOperations.put(key, String.valueOf(liveId), live);

//            writeHash(key, live.getLiveId());
            return;
        }
        System.err.println(" [ ERROR 0618T0648 ] 잘못된 요청입니다. ");
    }

    // 라이브 상태 업데이트
    public void currentLive(long liveId) {
        String key = String.join("LIVE", String.valueOf(liveId));
        if (isContain(key, liveId) == false) {
            System.err.println(" [ ERROR 0618T0719 ] 해당 라이브는 존재하지 않습니다. ");
            return;
        }
        LiveStatus stored = hashOperations.get(key, String.valueOf(liveId));

    }


    // 라이브 중간 참여 요청 : 레디스에서 데이터 불러오기록


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
