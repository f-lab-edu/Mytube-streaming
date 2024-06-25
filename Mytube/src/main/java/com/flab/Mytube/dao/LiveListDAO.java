package com.flab.Mytube.dao;

import com.flab.Mytube.vo.LiveStreamingVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class LiveListDAO implements LiveStreamingDAO {

  @Inject
  private SqlSession sql;

  private static String namespace = "com.flab.Mytube.mapper.ReadMapper";

  @Override
  public List<LiveStreamingVO> list() throws Exception {
    return sql.selectList(namespace + ".list");
  }
}
