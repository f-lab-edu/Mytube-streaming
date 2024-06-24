package com.flab.Mytube.dao;

import com.flab.Mytube.vo.User;
import org.apache.ibatis.session.SqlSession;

public class UserDao {

  private SqlSession sqlSession;

  public void setSqlSession(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  public User getUser(String userId) {
    return sqlSession.selectOne("org.mybatis.spring.sample.mappers.UserMapper.getUser", userId);
  }
}
