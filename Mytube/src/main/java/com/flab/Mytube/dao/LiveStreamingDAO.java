package com.flab.Mytube.dao;

import com.flab.Mytube.vo.LiveStreamingVO;

import java.util.List;

public interface LiveStreamingDAO {
    public List<LiveStreamingVO> list() throws Exception;
}
