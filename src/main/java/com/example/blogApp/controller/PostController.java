package com.example.blogApp.controller;

import com.example.blogApp.models.Post;
import com.example.blogApp.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/posts/add")
    public String createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @GetMapping("/api/posts/get")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/api/posts/get/{id}")
    public Post getPostById(@PathVariable("id") Long id) {
        return postService.getPostById(id);
    }

    @PutMapping("/api/posts/update/{id}")
    public Post updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    @DeleteMapping("/api/posts/delete/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        return postService.deletePost(id);
    }

}
