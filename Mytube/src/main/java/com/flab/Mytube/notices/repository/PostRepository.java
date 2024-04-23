package com.flab.Mytube.notices.repository;

import com.flab.Mytube.notices.vo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostRepository {
    void save(@Param("post") Post post);
    List<Post> findAll();
    Post findOne(@Param("post") Long id);
    void updatePost(@Param("post") Post post);
    void deletedById(@Param("post") Long id);
}
