package com.flab.Mytube.vo;

import com.flab.Mytube.dao.MovieListDAO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class MovieList implements MovieListDAO {

  @Inject
  private SqlSession sql;
  private static String namespace = "com.flab.Mytube.mappers.PostMapper";


  @Override
  public List<MovieVO> list() throws Exception {
    return sql.selectList("postMapper.list");
  }
}
