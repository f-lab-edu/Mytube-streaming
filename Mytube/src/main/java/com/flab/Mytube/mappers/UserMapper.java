package com.flab.Mytube.mappers;

import com.flab.Mytube.domain.User;
import com.flab.Mytube.dto.movie.request.ThumbsUpRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
  public User findByUserId(long userId);

  boolean checkPrefer(ThumbsUpRequest request);

  void updatePrefer(ThumbsUpRequest request);

  void deletePrefer(ThumbsUpRequest request);

  void addPrefer(ThumbsUpRequest request);
}
