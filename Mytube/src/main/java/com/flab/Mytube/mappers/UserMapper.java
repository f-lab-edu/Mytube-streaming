package com.flab.Mytube.mappers;

import com.flab.Mytube.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
  public User findByUserId(long userId);
}
