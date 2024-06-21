package com.flab.Mytube.service;

import com.flab.Mytube.dto.movie.request.ThumbsUpRequest;
import com.flab.Mytube.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  @Autowired
  private final UserMapper userMapper;

  // 좋아요 서비스
  public void prefer(ThumbsUpRequest request) {
    if (userMapper.checkPrefer(request) > 0) { // 좋아요 테이블이 1개 이상 존재
      request.minusCount();
      userMapper.updatePrefer(request);
      userMapper.deletePrefer(request);
      return;
    }
    //    2. 좋아요를 누르지 않은 경우 :탐색 -> thumbs_up 값 1 올려서 update
    request.plusCount();
    userMapper.addPrefer(request);
    userMapper.updatePrefer(request);
  }
}
