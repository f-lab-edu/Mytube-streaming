package com.flab.Mytube.Utils;

import com.flab.Mytube.dto.streaming.LiveStatus;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

public class TimeManager implements Callable<Integer> {
    // schedule(TimerTask task, long delay) : delay 시간 이후 task 실행
    // Timer(String name) : Creates a new timer whose associated thread has the specified name.
    // TimerTask(): 수행되는 내용을 run() 메소드를 재정의함으로써 실행
    // 타이머 시작: 10초마다 livestatus currenttime 업데이트

    private LiveStatus status;
    public TimeManager(LiveStatus status){
        this.status=status;
    }

    @Override
    public Integer call() throws Exception {
        TimerTask task = new TimerTask(){
            public void run() {
                status.updateCurrent();
            }
        };
        Timer t = new Timer();
        do{
            t.schedule(task, 500); //TODO: 10000:10초
        }while(status.isLiveOn());
        System.out.println(status.getCurrentTime());
        return status.getCurrentTime();
    }
}
