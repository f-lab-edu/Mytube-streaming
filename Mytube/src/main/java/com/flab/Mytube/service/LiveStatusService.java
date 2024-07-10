package com.flab.Mytube.service;

import com.flab.Mytube.error.exceptions.ResourceNotFoundException;
import com.flab.Mytube.dto.movie.request.WatchLiveRequest;
import com.flab.Mytube.dto.streaming.LiveStatus;
import com.flab.Mytube.utils.Movies;
import com.flab.Mytube.utils.Validations;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalTime;


@Slf4j
@Service
public class LiveStatusService {

  @Resource(name = "statusTemplate")
  private HashOperations<String, String, LiveStatus> hashOperations;

  private boolean contains(String key, long id) {
    if (hashOperations.hasKey(key, String.valueOf(id))) {
      return true;
    }
    return false;
  }

  // 라이브 시작
  public void startLive(long liveId, String url) {
    String key = String.valueOf(liveId);
    if (contains(key, liveId)) {
      throw new ResourceNotFoundException("이미 시작한 라이브 입니다.");
    }
    // 새로운 객체 캐시에 작성
    LiveStatus status = new LiveStatus(liveId, url);
    hashOperations.put(key, String.valueOf(liveId), status);
  }

  // 라이브 재시작
  public void restartLive(long liveId) {
    String key = String.valueOf(liveId);
    if (contains(key, liveId)) {
      LiveStatus live = hashOperations.get(key, String.valueOf(liveId));
      live.startLive();
      hashOperations.put(key, String.valueOf(liveId), live);
      return;
    }
    throw new ResourceNotFoundException("요청한 라이브 정보가 존재하지 않습니다.");
  }

  // 라이브 일시 정지
  public void stopLive(long liveId) {
    String key = String.valueOf(liveId);
    if (contains(key, liveId)) {
      LiveStatus live = hashOperations.get(key, String.valueOf(liveId));
      live.stopLive();
      hashOperations.put(key, String.valueOf(liveId), live);
      return;
    }
    throw new ResourceNotFoundException("요청한 라이브 정보가 존재하지 않습니다.");
  }

  // 라이브 종료
  public void endLive(long liveId) {
    String key = String.valueOf(liveId);
    if (contains(key, liveId)) {
      LiveStatus live = hashOperations.get(key, String.valueOf(liveId));
      live.endLive();
      hashOperations.delete(key, String.valueOf(liveId));
      return;
    }
    throw new ResourceNotFoundException("요청한 라이브 정보가 존재하지 않습니다.");
  }

  // 라이브 중간에 참여 요청 : 레디스에서 진행도 데이터 불러오기
  public File joinLive(WatchLiveRequest request) {
    int id = request.getLiveId();
    String key = String.valueOf(id);
    if (!hashOperations.hasKey(key, String.valueOf(id))) {
      throw new ResourceNotFoundException("해당 라이브는 존재하지 않습니다.");
    }

    LiveStatus stored = hashOperations.get(key, String.valueOf(id));
    try {
      if (Validations.notValidLive(stored) != true) {
        return null;
      }
    } catch (Exception e) {
      System.err.println("요청 내용을 확인해주세요.");
    }

    //  liveId 로 id 가 건너올 경우 -> return m3u8;
    if (Validations.isNumeric(request.getChannelId()) == true) {
      // stored 에서 이어보게 될 구간 확인
      return Movies.getFfmpegBuilder(stored.getM3u8Url(), stored.getTsIndex());
    }
    return new File(stored.getBasePath(request.getChannelId()));
  }

  // 라이브 상태 업데이트
  public void currentLive(long liveId, LocalTime time) {
    String key = String.valueOf(liveId);
    if (contains(key, liveId) == false) {
      throw new ResourceNotFoundException("해당 라이브는 존재하지 않습니다.");
    }
    LiveStatus stored = hashOperations.get(key, String.valueOf(liveId));
    stored.updateCurrent(time);
    hashOperations.put(key, key, stored);
  }
}
