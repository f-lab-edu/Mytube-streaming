//package com.flab.Mytube.service;
//
//import com.flab.Mytube.dto.streaming.LiveStatus;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//public class LiveStatusServiceTest {
//    LiveStatusService service = new LiveStatusService();
//    static LiveStatus status = new LiveStatus(2);
//
//    @Test
//    public void start(){
//        service.liveOn(status.getLiveId());
//        assertEquals(status.getStatus(), "LIVE_ON");
//    }
//    @Test
//    public void stop(){
//        service.liveStop(status.getLiveId());
//        assertEquals(status.getStatus(), "LIVE_STOP");
//    }
//    @Test
//    public void restart(){
//        service.liveOn(status.getLiveId());
//        assertEquals(status.getStatus(), "LIVE_ON");
//    }
//    @Test
//    public void end(){
//        service.liveEnd(status.getLiveId());
//        assertEquals(status,null);
//    }
//}