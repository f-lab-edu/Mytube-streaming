package com.flab.Mytube.dao;

import com.flab.Mytube.vo.MovieVO;

import java.util.List;

public interface MovieListDAO {
    public List<MovieVO> list() throws Exception;

}
