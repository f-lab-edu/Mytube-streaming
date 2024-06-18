package com.flab.Mytube.Utils;

import com.flab.Mytube.dto.streaming.LiveStatus;

public class TimeManager {
    // schedule(TimerTask task, long delay) : delay 시간 이후 task 실행
    // Timer(String name) : Creates a new timer whose associated thread has the specified name.
    // TimerTask(): 수행되는 내용을 run() 메소드를 재정의함으로써 실행
    // 타이머 시작: 10초마다 livestatus currenttime 업데이트

    private LiveStatus status;
    public TimeManager(LiveStatus status){
        this.status=status;
    }

    public Integer calling(){
        // status.isLiveOn()이 초기에는 true이고, 이후에 false가 되어 루프를 종료한다고 가정
//        int index=0; // 임시로 5회 설정
        while (status.isLiveOn()) {
            System.out.println(">>> call method >>> ");
            try{
                Thread.sleep(1000); // 1초
                status.updateCurrent();
                System.out.println("manager code >>> "+status.getStatus());
//                index++;
            } catch (InterruptedException e){
                e.printStackTrace();
            }
//            if(index >= 5) break;
        }

        System.out.println(status.getCurrentTime());
        return status.getCurrentTime();
    }
}
