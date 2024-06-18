package com.flab.Mytube.service;

import com.flab.Mytube.dto.streaming.LiveStatus;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class LiveStatusService {
//    private RedisOperations<String, Object> operations;
    @Resource(name = "statusTemplate")
    private RedisTemplate<String, LiveStatus> limitRedisTemplate;
//    private HashMap<String, LiveStatus> map = new HashMap<>();
    HashMapper<Object, byte[], byte[]> mapper = new ObjectHashMapper();

//    @Resource(name = "statusTemplate")
//    HashOperations<String, byte[], byte[]> hashOperations;
    @Resource(name = "statusTemplate")
    private HashOperations<String, String, LiveStatus> hashOperations;
    @Resource(name = "redisTemplate")
    private ListOperations<String, Object> listOps;

    private static String START="restart"; // 상수 모으는 파일 만들기
    private static String END="end"; // 상수 모으는 파일 만들기
    private static String STOP="stop"; // 상수 모으는 파일 만들기

    public void writeHash(long id) {

        String key = String.join("LIVE", String.valueOf(id));
        LiveStatus status = new LiveStatus(id);
//        map.put(key, status);

//        LiveStatus stored = map.get(key);
//        System.out.println(stored.toString());


        Map<byte[], byte[]> mappedHash = mapper.toHash(status);
//        hashOperations.putAll(key, mappedHash);
        hashOperations.put(key, String.valueOf(id), status);
    }

    // inject the template as ListOperations
//    @Resource(name="redisTemplate")
//    private ListOperations<String, Object> listOps;

    public void addLink(String userId, URL url) {
        listOps.leftPush(userId, url.toExternalForm());
    }

//    public void statusManager(long id, String work){
//        String key = String.join("LIVE", String.valueOf(id));
//        if(!map.containsKey(key)){
//            if(work== START){
//                map.put(key, new LiveStatus(id));
//                return;
//            }
//            // key 가 존재하지 않을 때마다 대처할 함수 설치?
//            return;
//        }
//        LiveStatus live = map.get(key);
//        live.changeState(work);
//    }

//    public void liveOn(long id){
//        String key = String.join("LIVE", String.valueOf(id));
//        if(!map.containsKey(key)){
//            map.put(key, new LiveStatus(id));
//            return;
//        }
//        LiveStatus live = map.get(key);
//        live.changeState(START);
//    }
//    public void liveStop(long id){
//        String key = String.join("LIVE", String.valueOf(id));
//        if(!map.containsKey(key)){
//            map.put(key, new LiveStatus(id));
//            return;
//        }
//        LiveStatus live = map.get(key);
//        live.changeState(STOP);
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
//
//    public String containLive(long id){
//        String key = String.join("LIVE", String.valueOf(id));
//        if(map.containsKey(key)){
//            return key;
//        }
//        return null;
//    }

}
