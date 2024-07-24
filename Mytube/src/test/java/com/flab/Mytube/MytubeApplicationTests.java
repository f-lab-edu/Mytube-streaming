package com.flab.Mytube;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
public class MytubeApplicationTests {

  @Autowired
  private SqlSessionTemplate sqlSession;

  @Test
  public void contextLoads() {
  }

  @Test
  public void testSqlSession() throws Exception {
    System.out.println(sqlSession.toString());
  }

  @Configuration
  static class TestConfiguration {

  }
}
