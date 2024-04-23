package com.flab.Mytube.notices.service;

import com.flab.Mytube.notices.repository.PostRepository;
import com.flab.Mytube.notices.vo.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;
    public PostService(PostRepository postRepository){
        this.postRepository=postRepository;
    }

    public void addPost(Post post){
        postRepository.save(post);
    }

    public List<Post> getPostList(){
        return postRepository.findAll();
    }

    public Post getPost(Long id){
        return postRepository.findOne(id);
    }

    public void updatePost(Post post){
        postRepository.updatePost(post);
    }

    public void deletePost(Long id){
        postRepository.deletedById(id);
    }
}
