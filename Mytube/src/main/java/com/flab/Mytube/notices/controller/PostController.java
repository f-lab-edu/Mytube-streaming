package com.flab.Mytube.notices.controller;

import com.flab.Mytube.notices.service.PostService;
import com.flab.Mytube.notices.vo.Post;
import com.flab.Mytube.notices.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping(value="/posts")
    public List<Post> getPostList(){
        return postService.getPostList();
    }

    @GetMapping(value="/post")
    public Post getPost(Long id){
        return postService.getPost(id);
    }

    @PostMapping(value="/post")
    public Result addPost(@RequestBody Post post){
        postService.addPost(post);
        return new Result(200, "Success");
    }

    @PutMapping(value="/post")
    public Result updatePost(@RequestBody Post post){
        postService.updatePost(post);
        return new Result(200, "Success");
    }

    @DeleteMapping("/post")
    public Result deletePost(Long id){
        postService.deletePost(id);
        return new Result(200, "Success");
    }
}
