package com.example.blogApp.controller;

import com.example.blogApp.dto.PostDTO;
import com.example.blogApp.models.Post;
import com.example.blogApp.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public String createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }

    @GetMapping
    public List<PostDTO> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable("id") Long id) {
        return postService.getPostById(id);
    }

    @PutMapping("/update/{id}")
    public Post updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        return postService.deletePost(id);
    }

}
