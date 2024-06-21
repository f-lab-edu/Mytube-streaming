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
    // 테스트에 필요한 추가 구성 요소를 여기에 정의할 수 있습니다.
  }
}
