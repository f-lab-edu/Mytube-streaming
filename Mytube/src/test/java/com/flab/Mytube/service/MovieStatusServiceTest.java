package com.flab.Mytube.service;

import com.flab.Mytube.dto.streaming.LiveStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
class MovieStatusServiceTest {
    static Logger logger = LoggerFactory.getLogger(MovieStatusServiceTest.class);
    @Autowired
    MovieStatusService movieStatusService;
//    @Test
//    public void test(){
//        for (int count = 1; count <= 10; count++) {
//            logger.info("SLF4J-API와 Logback을 사용하는 로깅 재밌어 {}", count);
//        }
//    }
    String PREFIX = "LIVE_STATUS:";
//    @Test
//    public void 상태가_update_되었습니다() {
//        long id = 1;
//        String status="LIVE_ON";
//        int currentTime=10;
//        movieStatusService.updateStatus(id, status, currentTime);
//    }
    @Test
    public void 상태_생성(){
        long id = 1;
        String key = PREFIX+id;
        String status = "LIVE_ON";
        int currentTime=10;
        movieStatusService.writeHash(key, new LiveStatus(id, status, currentTime));
    }

//    @Test
//    public void LiveStatus가_불러왔습니다() {
//        long id = 1;
//        LiveService=movieStatusService.getStatus(id);
//        String status="LIVE_ON";
//        int currentTime=10;
//        movieStatusService.updateStatus(id, status, currentTime);
//    }



}